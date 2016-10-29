package com.daimler.otr.controller;

import com.spring.boot.practice.TestProfileValueSource;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by wangchong on 9/27/16.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
@ProfileValueSourceConfiguration(TestProfileValueSource.class)
@IfProfileValue(name = "env", value = "local")
public class CustomerRestControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("target/generated-snippets");

    private static final Logger logger = Logger.getLogger(CustomerRestControllerTest.class);

    protected final LinksSnippet pagingLinks = links(
            linkWithRel("first").optional().description("The first page of results"),
            linkWithRel("last").optional().description("The last page of results"),
            linkWithRel("next").optional().description("The next page of results"),
            linkWithRel("prev").optional().description("The previous page of results"));

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        logger.info("setup.............................");
        /*this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(documentationConfiguration(this.restDocumentation).uris()
                .withScheme("https")
                .withHost("example.com")
                .withPort(443)).build();*/
        //this.mockMvc = MockMvcBuilders.standaloneSetup(new CustomerRestController()).build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        logger.info("setup is done.....................");

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getUser() throws Exception {

        FieldDescriptor[] contact = new FieldDescriptor[]{
                fieldWithPath("name").description("name of the contact").attributes(key("constraints")
                        .value("Must not be null. Must not be empty")),
                fieldWithPath("tel").description("telephone number of the contact").attributes(key("constraints")
                        .value("Must not be null. Must not be empty"))};
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/app/customer/{customer_id}", "123").param("username", "Tester").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("Jack")).andExpect(jsonPath("$.Address").value("ShangHai"))
                .andExpect(content().string("{\"id\":\"123\",\"introduction\":\"it is me\",\"name\":\"Jack\",\"contacts\"" +
                        ":[{\"name\":\"Jack\",\"tel\":\"138119113\"},{\"name\":\"Linda\",\"tel\":\"08212333\"}],\"Address\":" +
                        "\"ShangHai\",\"JavaClassName\":\"com.spring.boot.practice.model.Customer\"}")); /*
                .andDo(document("index",
                        pathParameters(
                            parameterWithName("customer_id").description("Customer's id")
                        ),
                        requestParameters(
                            parameterWithName("username").description("The user's username")
                        ),
                        /*requestFields(
                            attributes(key("title").value("Fields for user creation")),
                            fieldWithPath("username").description("The user's name")
                                .attributes(key("constraints")
                                        .value("Must not be null. Must not be empty"))),*/
                        /*responseFields(
                            fieldWithPath("id").description("The user's id").attributes(key("constraints")
                                    .value("Must not be null. Must not be empty")),
                            fieldWithPath("JavaClassName").ignored().attributes(key("constraints")
                                    .value("Must not be null. Must not be empty")),
                            fieldWithPath("name").type(JsonFieldType.STRING).description("The user's name").attributes(key("constraints")
                                    .value("Must not be null. Must not be empty")),
                            fieldWithPath("introduction").description("The user's introduction").attributes(key("constraints")
                                    .value("Must not be null. Must not be empty")),
                            fieldWithPath("Address").description("The user's address").attributes(key("constraints")
                                    .value("Must not be null. Must not be empty")),
                            fieldWithPath("contacts").description("An array of contacts").attributes(key("constraints")
                                    .value("Must not be null. Must not be empty")),
                            fieldWithPath("contacts.[]").description("A contact details").attributes(key("constraints")
                                  .value("Must not be null. Must not be empty")),
                            fieldWithPath("contacts.[].name").description("A contact name").attributes(key("constraints")
                                        .value("Must not be null. Must not be empty"))
                                )//.andWithPrefix("contacts.[].", contact)
                ));
                        //)));*/

    }

    @Test
    public void calculate() throws Exception {

    }

    @Test
    public void createCustomer() throws Exception {

    }

}