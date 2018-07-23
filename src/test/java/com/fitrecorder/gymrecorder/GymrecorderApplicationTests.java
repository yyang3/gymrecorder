package com.fitrecorder.gymrecorder;

import com.fitrecorder.gymrecorder.beans.User;
import com.fitrecorder.gymrecorder.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GymrecorderApplicationTests {

    private MockMvc mvc;
    private RequestBuilder request;

    @Autowired
//    private IUserService userService;
            UserController userController;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        User user = new User();
        user.setSurName("yang");
        user.setGivenName("test");
        user.setUserName("user1");
        user.setPwd("test");

        request = post("/users").flashAttr("user", user);

        mvcSendAndGetResult(request);

        countUsers("/users/count", "1");

        request = get("/users");

        MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    private void countUsers(String s, String s2) throws Exception {
        request = get(s);

        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo(s2)));
    }

    @Test
    public void testEmptyUserList() throws Exception {
        countUsers("/users", "[]");
    }

    @Test
    @Transactional
    public void testAddAndRemove() throws Exception {
        request = post("/users");
        User user = new User();
        user.setSurName("yang");
        user.setGivenName("test");
        user.setUserName("user1");
        user.setPwd("test");

        request = post("/users").flashAttr("user", user);

        mvcSendAndGetResult(request);

        countUsers("/users/count", "1");

        request = delete("/users/user").flashAttr("userName", "user1");

        mvcSendAndGetResult(request);

        countUsers("/users/count", "0");
    }

    private void mvcSendAndGetResult(RequestBuilder request) throws Exception {
        mvc.perform(request).andExpect(status().isOk());
    }

}
