package com.glqdlt.ex.springrestdocs;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author Jhun
 * 2019-04-25
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = CounterRestController.class, secure = false)
@ActiveProfiles(value = "test")
public class CounterRestControllerTest {

    @MockBean
    private CounterService counterService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greeting() throws Exception {

        final String stub = "tester nick to meet you";
        Mockito.when(counterService.greeting("tester")).thenReturn(stub);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tester/greeting"))
                .andExpect(
                        (r) -> {
                            int status = r.getResponse().getStatus();
                            Assert.assertEquals(200, status);
                        }
                )
                .andExpect(
                        (r) -> {
                            String message = r.getResponse().getContentAsString();
                            Assert.assertEquals(stub, message);
                        }
                );

    }
}