package com.pozharsky.dmitri.util;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PageNumberReducer implements NumberReduceable<String,List<Integer>> {

    public String reduce(List<Integer> list) {
        if(list == null || list.size() == 0) throw new IllegalArgumentException("List with number of page can't be null or empty");
        Collections.sort(list);
        StringBuilder stringBuilder = new StringBuilder();
        Integer firstNumber = 0;
        Integer lastNumber = 0;
        for(int i =0;i<list.size();i++){
            if(firstNumber == 0){
                firstNumber = list.get(i);
            }else {
                lastNumber = list.get(i);
            }
            if (list.size()!= i+1 && list.get(i+1)-list.get(i) == 1) {
                continue;
            }
            if(lastNumber == 0){
                stringBuilder.append(firstNumber);
                if(list.size() != i+1) stringBuilder.append(",");
                firstNumber = 0;
            } else {
                stringBuilder.append(String.join("-",firstNumber.toString(),lastNumber.toString()));
                if(list.size() != i+1) stringBuilder.append(",");
                firstNumber = lastNumber = 0;
            }
        }
        return stringBuilder.toString();
    }
}
