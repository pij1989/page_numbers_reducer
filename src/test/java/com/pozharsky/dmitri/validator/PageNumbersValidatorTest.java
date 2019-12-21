package com.pozharsky.dmitri.validator;

import com.pozharsky.dmitri.configuration.TestAppConfig;
import com.pozharsky.dmitri.dto.PageNumbersDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.DataBinder;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
public class PageNumbersValidatorTest {

    @Autowired
    private PageNumbersValidator pageNumbersValidator;

    @Test
    public void testSupports() {
        //Given
        Class<PageNumbersDto> pageNumbersDtoClass = PageNumbersDto.class;
        //When
        boolean result = pageNumbersValidator.supports(pageNumbersDtoClass);
        //Then
        assertTrue(result);
    }

    @Test
    public void testValidateWithoutErrors() {
        //Given
        PageNumbersDto pageNumbersDto = new PageNumbersDto();
        pageNumbersDto.setRawPageNumbers("1,3,32,5,11,7,6,19,2,21,4,8,22,23");

        DataBinder dataBinder = new DataBinder(pageNumbersDto);
        dataBinder.addValidators(pageNumbersValidator);
        //When
        dataBinder.validate();
        //Then
        assertFalse(dataBinder.getBindingResult().hasErrors());
    }

    @Test
    public void testValidateWithErrors() {
        //Given
        PageNumbersDto pageNumbersDto = new PageNumbersDto();
        pageNumbersDto.setRawPageNumbers("01,3,32-,5,11,7,6,19,2,21,4,8,22,23,");

        DataBinder dataBinder = new DataBinder(pageNumbersDto);
        dataBinder.addValidators(pageNumbersValidator);
        //When
        dataBinder.validate();
        //Then
        assertTrue(dataBinder.getBindingResult().hasErrors());
    }

}