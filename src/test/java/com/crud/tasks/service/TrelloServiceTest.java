package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.dto.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testFetchTrelloBoards() {
        // Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        List<TrelloListDto> trelloListDtos = new ArrayList<>();

        trelloListDtos.add(new TrelloListDto("1", "List", false));
        trelloBoardDtos.add(new TrelloBoardDto("1", "Board", trelloListDtos));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);

        // When
        List<TrelloBoardDto> trelloBoardDtos1 = trelloService.fetchTrelloBoards();

        // Then
        assertEquals(1, trelloBoardDtos1.size());
    }

    @Test
    public void testCreateTrelloCard() {
        // Given
        TrelloDto trelloDto = new TrelloDto(1, 2);
        AttachmentsByTypeDto attachmentsByTypeDto = new AttachmentsByTypeDto(trelloDto);
        BadgesDto trelloBadgesDto = new BadgesDto(attachmentsByTypeDto);
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card", "TestCardDescription", "TestPos", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Card", "URL", trelloBadgesDto);

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        // When
        CreatedTrelloCardDto createdTrelloCard = trelloService.createTrelloCard(trelloCardDto);

        // Then
        assertEquals("1", createdTrelloCard.getId());
        assertEquals("Card", createdTrelloCard.getName());
        assertEquals("URL", createdTrelloCard.getShortUrl());
    }
}
