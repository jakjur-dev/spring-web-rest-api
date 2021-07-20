package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.dto.TrelloBoardDto;
import com.crud.tasks.dto.TrelloCardDto;
import com.crud.tasks.dto.TrelloListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void mapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("test1", "test1", new ArrayList<>()));
        trelloBoardDtoList.add(new TrelloBoardDto("test2", "test2", new ArrayList<>()));
        trelloBoardDtoList.add(new TrelloBoardDto("test3", "test3", new ArrayList<>()));

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(3, trelloBoardList.size());

    }

    @Test
    void mapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("test1", "test1", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("test2", "test2", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("test3", "test3", new ArrayList<>()));

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(3, trelloBoardDtoList.size());
    }

    @Test
    void mapToList() {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("test1", "test1", true));
        trelloListDtoList.add(new TrelloListDto("test2", "test2", true));
        trelloListDtoList.add(new TrelloListDto("test3", "test3", true));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(3, trelloLists.size());
    }

    @Test
    void mapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("test1", "test1", true));
        trelloLists.add(new TrelloList("test2", "test2", true));
        trelloLists.add(new TrelloList("test3", "test3", true));

        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(3, trelloListDtoList.size());
    }

    @Test
    void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("Test task", trelloCardDto.getName());
        assertEquals("Test Description", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("test_id", trelloCardDto.getListId());
    }

    @Test
    void mapToCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Test task", trelloCard.getName());
        assertEquals("Test Description", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("test_id", trelloCard.getListId());
    }
}