package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DbService dbService;

    @MockBean
    TaskMapper taskMapper;

    @Test
    public void shouldFetchEmptyListOfTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        when(dbService.getAllTasks()).thenReturn(taskList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetListOfTasks() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "test task", "this is the test task");
        taskList.add(task);
        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "test taskdto", "this is the test taskdto");
        taskDtoList.add(taskDto);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        when(dbService.getAllTasks()).thenReturn(taskList);
        //When&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test taskdto")))
                .andExpect(jsonPath("$[0].content", is("this is the test taskdto")));
    }

    @Test
    public void shouldGetOneTask() throws Exception {
        //Given
        Task task = new Task(1l, "test", "testing");
        TaskDto taskDto = new TaskDto(1l, "test", "testing");

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.getTaskBasedOnId(1L)).thenReturn(Optional.of(task));

        //When  & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("testing")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "testing");
        Task task = new Task(1L, "test", "testing");

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.saveTask(any())).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(dbService,times(1)).saveTask(any());
    }



    @Test
    public void shouldDeleteTask() throws Exception {
        //When % Then
        mockMvc.perform(delete("/v1/task/deleteTask").param("taskId","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(dbService,times(1)).deleteTask(any());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test content");
        Task task = new Task(1L, "test", "test content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        when(taskMapper.mapToTask(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);
        when(dbService.saveTask(task)).thenReturn(task);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("test content")))
                .andExpect(status().isOk());
        }
}
