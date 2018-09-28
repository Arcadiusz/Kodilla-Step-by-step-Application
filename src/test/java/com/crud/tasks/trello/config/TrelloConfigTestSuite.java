package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {

    @Autowired
    public TrelloConfig trelloConfig;

    @Test
    public void testTrelloConfig() {

        //Given

        //When
        String apiKey = trelloConfig.getTrelloAppKey();
        String apiToken = trelloConfig.getTrelloToken();
        String username = trelloConfig.getUsername();
        String apiEndpoint = trelloConfig.getTrelloApiEndpoint();

        //Then
        Assert.assertNotEquals("asafsfsfsfsfsd", apiKey);
        Assert.assertNotEquals("sadasdasdasdd", apiToken);
        Assert.assertEquals("arcadiusz", username);
        Assert.assertEquals("https://api.trello.com/1", apiEndpoint);

    }
}
