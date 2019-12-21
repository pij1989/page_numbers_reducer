package com.pozharsky.dmitri.validator;

import com.pozharsky.dmitri.dto.PageNumbersDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class PageNumbersValidator implements Validator {
    private static final String RAW_PAGE_NUMBERS_REGEX = "^(([1-9]|[1-9][0-9]+)\\,)*([1-9]|[1-9][0-9]+)$";
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PageNumbersDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target == null){
            errors.reject("object.null","Raw page numbers is null");
            return;
        }
        PageNumbersDto rawPageNumbers = (PageNumbersDto)target;
        Pattern pattern = Pattern.compile(RAW_PAGE_NUMBERS_REGEX);
        if(!pattern.matcher(rawPageNumbers.getRawPageNumbers().trim()).matches()){
            errors.rejectValue("rawPageNumbers","page_numbers.invalid_format","Page numbers must be integer more then 0 and separated comma");
        }
    }
}
