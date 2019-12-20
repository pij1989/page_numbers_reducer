package com.pozharsky.dmitri.controller;

import com.pozharsky.dmitri.service.PageNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageNumbersController {

    @Autowired
    private PageNumbersService pageNumbersService;

    @GetMapping("/reducedPageNumbers")
    public String showReduceNumbersPage(@RequestParam(name = "rawPageNumbers") String rawPageNumbers, Model model){
        model.addAttribute("reducePageNumbers",pageNumbersService.getReducePageNumbers(rawPageNumbers));
        return "reduceNumbersPage";
    }

}
