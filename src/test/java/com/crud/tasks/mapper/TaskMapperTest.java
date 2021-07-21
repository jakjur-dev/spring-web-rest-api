package com.crud.tasks.mapper;


import com.crud.tasks.domain.Task;
import com.crud.tasks.dto.TaskDto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    private Task task;
    private TaskDto taskDto;
    private List<Task> taskList;

    @Test
    public void testMapToTask() {
        // Given
        taskDto = new TaskDto(1L, "TaskDto", "ContentDto");

        // When
        Task task = taskMapper.mapToTask(taskDto);

        // Then
        assertEquals(ofNullable(1L).get(), task.getId());
        assertEquals("TaskDto", task.getTitle());
        assertEquals("ContentDto", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        // Given
        task = new Task(1L, "Task", "Content");

        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        // Then
        assertEquals(ofNullable(1L).get(), taskDto.getId());
        assertEquals("Task", taskDto.getTitle());
        assertEquals("Content", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        // Given
        taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Task", "Content"));

        // When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);

        // Then
        assertFalse(taskDtos.isEmpty());
        assertEquals(1, taskDtos.size());
    }
}
