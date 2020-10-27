package com.lab2.auth;

import com.lab2.auth.models.Login;
import com.lab2.auth.models.User;
import com.lab2.auth.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource(locations="classpath:application.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthApplicationTests {

    private boolean initialized = false;
    @Autowired
    UserService userService;


    
    @Test
    public void contextLoads() {
    }

    @Before
    public void setUpDB(){
        if (!initialized){
            userService.addUser(new User("test", "password"));
            userService.addUser(new User("log", "pass"));
        }
        initialized = true;
    }

    // Покрытие тестами валидации полей login, password (2+, 2-)
    @Test
    public void testValidateUserPos1() {
        Login login = new Login();
        login.setUsername("test");
        login.setPassword("password");

        User user = userService.validateUser(login);
        Assert.assertEquals("test", user.getUsername());
    }

    @Test
    public void testValidateUserPos2(){
        Login login = new Login();
        login.setUsername("log");
        login.setPassword("pass");

        User user = userService.validateUser(login);
        Assert.assertEquals("pass", user.getPassword());
    }

    @Test
    public void testValidateUserNeg1(){
        Login login = new Login();
        login.setUsername("log1");
        login.setPassword("pass");

        User user = userService.validateUser(login);
        Assert.assertNull(user);
    }

    @Test
    public void testValidateUserNeg2(){
        Login login = new Login();
        login.setUsername("log");
        login.setPassword("pass1");

        User user = userService.validateUser(login);
        Assert.assertNull(user);
    }

    //Проверка существовоания пользователя в БД (1+, 1-)
    @Test
    public void testExistPos(){
        User user = userService.findByUsername("test");
        Assert.assertNotNull(user);
    }

    @Test
    public void testExistNeg(){
        User user = userService.findByUsername("test1");
        Assert.assertNull(user);
    }

    //Добавление данных в БД (1+)
    @Test
    public void testSqlInsert(){
        User user = new User("test", "password");
        userService.addUser(user);
        User user_out = userService.findByUsername("test");
        Assert.assertEquals(user, user_out);
    }



}
