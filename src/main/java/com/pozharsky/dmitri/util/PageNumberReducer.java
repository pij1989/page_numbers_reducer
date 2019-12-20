package com.pozharsky.dmitri.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PageNumberReducer implements NumberReduceable<String,List<Integer>> {

    public String reduce(List<Integer> pageNumbers) {
        if(pageNumbers == null || pageNumbers.size() == 0) throw new IllegalArgumentException("List with number of page can't be null or empty");
        pageNumbers = pageNumbers.stream()
                .sorted()
                .distinct()
                .collect(Collectors.toList());
        if(pageNumbers.get(0)==0) throw new PageNumberFormatException("Page number can't be 0");
        StringBuilder stringBuilder = new StringBuilder();
        Integer firstNumber = 0;
        Integer lastNumber = 0;
        for(int i =0;i<pageNumbers.size();i++){
            if(firstNumber == 0){
                firstNumber = pageNumbers.get(i);
            }else {
                lastNumber = pageNumbers.get(i);
            }
            if (pageNumbers.size()!= i+1 && pageNumbers.get(i+1)-pageNumbers.get(i) == 1) {
                continue;
            }
            if(lastNumber == 0){
                stringBuilder.append(firstNumber);
                if(pageNumbers.size() != i+1) stringBuilder.append(",");
                firstNumber = 0;
            } else {
                stringBuilder.append(String.join("-",firstNumber.toString(),lastNumber.toString()));
                if(pageNumbers.size() != i+1) stringBuilder.append(",");
                firstNumber = lastNumber = 0;
            }
        }
        return stringBuilder.toString();
    }
}
