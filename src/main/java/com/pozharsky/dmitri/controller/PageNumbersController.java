package com.pozharsky.dmitri.controller;

import com.pozharsky.dmitri.dto.PageNumbersDto;
import com.pozharsky.dmitri.exception.PageNumberFormatException;
import com.pozharsky.dmitri.exception.PageNumbersNotReducedException;
import com.pozharsky.dmitri.service.PageNumbersService;
import com.pozharsky.dmitri.validator.PageNumbersValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PageNumbersController {

    private static final Logger logger = LogManager.getLogger(PageNumbersController.class);

    @Autowired
    private PageNumbersValidator pageNumbersValidator;

    @Autowired
    private PageNumbersService pageNumbersService;

    @GetMapping("/reducedPageNumbers")
    public String showReduceNumbersPage(@ModelAttribute PageNumbersDto pageNumbersDto, BindingResult result, Model model){
        pageNumbersValidator.validate(pageNumbersDto,result);
        if(result.hasErrors()){
            throw new PageNumberFormatException(result.getAllErrors().stream()
                    .findFirst()
                    .orElseThrow()
                    .getDefaultMessage());
        }
        model.addAttribute("reducePageNumbers",pageNumbersService.getReducePageNumbers(pageNumbersDto.getRawPageNumbers().trim()));
        return "reduceNumbersPage";
    }

    @ExceptionHandler({PageNumberFormatException.class, PageNumbersNotReducedException.class})
    public ModelAndView errorsHandler(Exception e){
        logger.error(e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",e.getMessage());
        modelAndView.setViewName("error");
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

}
