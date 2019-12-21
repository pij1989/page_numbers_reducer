package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.configuration.TestAppConfig;
import com.pozharsky.dmitri.exception.PageNumbersNotReducedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
public class PageNumbersServiceTest {

    @Autowired
    private PageNumbersService pageNumbersService;

    @Test (expected = PageNumbersNotReducedException.class)
    public void testGetReducePageNumbers() {
        //Given
        String rawPageNumbers = "1.0,3.0,32.0,5.0";
        //When
        pageNumbersService.getReducePageNumbers(rawPageNumbers);
    }
}