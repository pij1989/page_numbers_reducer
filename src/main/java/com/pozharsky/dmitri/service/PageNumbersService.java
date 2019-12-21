package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.exception.PageNumbersNotReducedException;
import com.pozharsky.dmitri.util.PageNumberReducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageNumbersService {
    private static final Logger logger = LogManager.getLogger(PageNumbersService.class);

    @Autowired
    private PageNumberReducer pageNumberReducer;

    public String getReducePageNumbers(String rawPageNumbers){
        try {
            List<Integer> pageNumbers = Arrays.stream(rawPageNumbers.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            String reducePageNumbers =  pageNumberReducer.reduce(pageNumbers);
            logger.info("Reduce page numbers: "+reducePageNumbers);
            return reducePageNumbers;
        } catch (Exception e) {
            logger.error(e);
            throw new PageNumbersNotReducedException("Page numbers can not be reduce",e);
        }
    }
}
