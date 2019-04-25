package com.glqdlt.ex.springrestdocs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Jhun
 * 2019-04-25
 */
public class CounterServiceTest {

    private CounterService counterService;

    @Before
    public void setUp() throws Exception {
        counterService = new CounterServiceImpl();
    }

    @Test
    public void greeting() {
        String message = counterService.greeting("tester");
        Assert.assertEquals("tester, nice to meet you.", message);
    }
}