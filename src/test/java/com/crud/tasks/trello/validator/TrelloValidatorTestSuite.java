package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {

    @Mock
    TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards() {

        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Real name", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "test", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        TrelloValidator trelloValidator = new TrelloValidator();
        List<TrelloBoard> filtredTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(1, filtredTrelloBoards.size());
    }

    @Test
    public void testValidateCard() {

        //Given
        TrelloCard trelloCard1 = new TrelloCard("test", "desc", "top", "1");
        TrelloCard trelloCard2 = new TrelloCard("real", "desc", "top", "2");

        //When
        trelloValidator.validateCard(trelloCard1);
        trelloValidator.validateCard(trelloCard2);

        //Then
        verify(trelloValidator, times(1)).validateCard(trelloCard1);
        verify(trelloValidator, times(1)).validateCard(trelloCard2);
    }
}