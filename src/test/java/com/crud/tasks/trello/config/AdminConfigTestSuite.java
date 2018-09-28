package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminConfigTestSuite {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    public void testGetAdminMail() {
        //Given

        //When
        String email = adminConfig.getAdminMail();

        //Then
        Assert.assertNotEquals("arek@gmail.com", email);
        Assert.assertEquals("a.urbanski2@gmail.com", email);
    }
}
