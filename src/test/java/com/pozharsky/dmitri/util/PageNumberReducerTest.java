package com.pozharsky.dmitri.util;

import com.pozharsky.dmitri.configuration.TestAppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
public class PageNumberReducerTest {
    private static final String TEST_ROW_PAGE_NUMBERS = "1,3,32,5,11,7,6,19,2,21,4,8,22,23";
    private List<Integer> list;

    @Autowired
    PageNumberReducer pageNumberReducer;

    @Before
    public void setUp() throws Exception {
        list = Arrays.stream(TEST_ROW_PAGE_NUMBERS.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Test
    public void testReduce() {
        String result = pageNumberReducer.reduce(list);
        assertThat(result, is("1-8,11,19,21-23,32"));
    }

    @After
    public void tearDown() throws Exception {
        list = null;
    }
}