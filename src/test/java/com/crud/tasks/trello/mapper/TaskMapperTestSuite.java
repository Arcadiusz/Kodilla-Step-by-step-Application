package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test task", "this is the test task");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(Long.valueOf(1L), task.getId());
        assertEquals("test task", task.getTitle());
        assertEquals("this is the test task", task.getContent());

    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "test task", "this is the test task");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(Long.valueOf(1L), taskDto.getId());
        assertEquals("test task", taskDto.getTitle());
        assertEquals("this is the test task", taskDto.getContent());

    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "test task", "this is the test task");
        Task task2 = new Task(2L, "test task 2", "this is the test task 2");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        taskList.remove(task2);
        //Then
        assertEquals(2, taskDtoList.size());
        assertEquals(Long.valueOf(1L), taskDtoList.get(0).getId());
        assertEquals("test task", taskDtoList.get(0).getTitle());
        assertEquals("this is the test task", taskDtoList.get(0).getContent());

    }
}
