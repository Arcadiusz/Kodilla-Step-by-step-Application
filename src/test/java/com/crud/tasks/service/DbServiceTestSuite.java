package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.stubVoid;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "dd", "dfg");
        taskList.add(task);
        when(taskRepository.findAll()).thenReturn(taskList);
        //When
        List<Task> fetchedTaskList = dbService.getAllTasks();
        //Then
        assertEquals(1, fetchedTaskList.size());
    }

    @Test
    public void testGetTaskById() {
        //Given
        Long id = 1L;
        Task task = new Task(1L, "dd", "dfg");
        when(taskRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(task));
        //When
        Optional<Task> task1 = dbService.getTaskBasedOnId(id);
        //Then
        assertNotEquals(null, task1);
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "dd", "dfg");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task savedTask = dbService.saveTask(task);
        //Then
        assertEquals("dd", savedTask.getTitle());
    }
}