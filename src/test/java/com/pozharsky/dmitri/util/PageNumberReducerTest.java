package com.pozharsky.dmitri.util;

import com.pozharsky.dmitri.configuration.TestAppConfig;
import com.pozharsky.dmitri.exception.PageNumberFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
public class PageNumberReducerTest {
    private static final String TEST_ROW_PAGE_NUMBERS = "1,3,32,5,11,7,6,19,2,21,4,8,22,23";

    @Autowired
    PageNumberReducer pageNumberReducer;

    @Test
    public void testReduceResultNotNull() {
        //Given
        List<Integer> list = Arrays.stream(TEST_ROW_PAGE_NUMBERS.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        //When
        String result = pageNumberReducer.reduce(list);
        //Then
        assertThat(result,notNullValue());
    }


    @Test
    public void testReduceRowPageNumbers() {
        //Given
        List<Integer> list = Arrays.stream(TEST_ROW_PAGE_NUMBERS.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        //When
        String result = pageNumberReducer.reduce(list);
        //Then
        assertThat(result, is("1-8,11,19,21-23,32"));
    }

    @Test
    public void testReduceOnePageNumbers() {
        //Given
        List<Integer> list = new ArrayList<>();
        list.add(3);
        //When
        String result = pageNumberReducer.reduce(list);
        //Then
        assertThat(result, is("3"));
    }

    @Test
    public void testReduceSequenceOfPageNumbersOneByOne() {
        //Given
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        //When
        String result = pageNumberReducer.reduce(list);
        //Then
        assertThat(result, is("1-3"));
    }

    @Test
    public void testReduceSequenceOfPageNumbersNotOneByOne() {
        //Given
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(7);
        list.add(10);
        //When
        String result = pageNumberReducer.reduce(list);
        //Then
        assertThat(result, is("1,7,10"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReduceArgumentIsNull() {
        //Given
        List<Integer> list = null;
        //When
        pageNumberReducer.reduce(list);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReduceArgumentSizeIsZero() {
        //Given
        List<Integer> list = new ArrayList<>();
        //When
        pageNumberReducer.reduce(list);
    }

    @Test(expected = PageNumberFormatException.class)
    public void testReducePageNumberIsZero() {
        //Given
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(7);
        //When
        pageNumberReducer.reduce(list);
    }

    @Test
    public void testReduceEqualPageNumbers() {
        //Given
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(3);
        list.add(3);
        //When
        String result = pageNumberReducer.reduce(list);
        //Then
        assertThat(result, is("3"));
    }



}