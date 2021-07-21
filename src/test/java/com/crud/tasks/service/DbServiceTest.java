package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Test
    public void testGetAllTasks() {
        // Given

        // When
        List<Task> taskList = dbService.getAllTasks();

        // Then
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void testCreateTask() {
        // Given
        Task task = new Task(1L, "Task", "Content");

        // When
        Task saveTask = dbService.saveTask(task);

        // Then
        assertEquals("Task", task.getTitle());
        assertEquals("Content", task.getContent());

        // CleanUp
        dbService.deleteTask(saveTask.getId());
    }
}
