package com.lab2.auth;

import com.lab2.auth.models.Login;
import com.lab2.auth.models.User;
import com.lab2.auth.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource(locations="classpath:application.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testValidateUser() {
        Login login = new Login();
        login.setUsername("ranjith");
        login.setPassword("sekar");

        User user = userService.validateUser(login);
        //Assert.assertEquals("Ranjith", user.getFirstname());
    }

    @Test
    public void testSqlInsert(){
        User user = new User("test", "password");
        userService.addUser(user);
        User user_out = userService.findByUsername("test");
        Assert.assertEquals(user, user_out);
    }



}
