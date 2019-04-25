package com.glqdlt.ex.springrestdocs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

/**
 * @author Jhun
 * 2019-04-25
 * @ses <a href='https://scacap.github.io/spring-auto-restdocs/#gettingstarted'>https://scacap.github.io/spring-auto-restdocs/#gettingstarted</a>
 * @see <a href='https://www.baeldung.com/spring-rest-docs">https://www.baeldung.com/spring-rest-docs</a>
 * @see <a href='https://docs.spring.io/spring-restdocs/docs/2.0.3.RELEASE/reference/html5/'>https://docs.spring.io/spring-restdocs/docs/2.0.3.RELEASE/reference/html5/</a>
 * @see <a href='https://www.baeldung.com/spring-rest-docs'>https://www.baeldung.com/spring-rest-docs</a>
 * @see <a href='https://java.ihoney.pe.kr/517'>https://java.ihoney.pe.kr/517</a>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = CounterRestController.class, secure = false)
@ActiveProfiles(value = "test")
public class CounterRestControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CounterService counterService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ))
                .build();
    }

    @Test
    public void greeting() throws Exception {

        final String stub = "tester nick to meet you";
        Mockito.when(counterService.greeting("tester")).thenReturn(stub);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/{user}/greeting", "tester"))
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
                )
                .andDo(document("user greeting"
                        , pathParameters(
                                parameterWithName("user").description("target user name")
                        )));

    }
}