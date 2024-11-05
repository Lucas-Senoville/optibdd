package com.exo1.exo1.service;

import com.exo1.exo1.dto.TaskDto;
import com.exo1.exo1.entity.Task;
import com.exo1.exo1.mapper.TaskMapper;
import com.exo1.exo1.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    @Cacheable(value = "tasks")
    public List<TaskDto> findAll() {
        return taskMapper.toDtos(taskRepository.findAllTasks());
    }

    @Cacheable(value = "tasks", key = "#id")
    public TaskDto findById(long id) {
        return taskMapper.toDto(taskRepository.findTaskById(id).orElse(null));
    }

    @CachePut(value = "tasks", key = "#id")
    public TaskDto save(TaskDto taskDto) {
        return taskMapper.toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
    }

    @CachePut(value = "tasks", key = "#id")
    public TaskDto update(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found with id " + id));
        taskDto.setId(existingTask.getId());
        return taskMapper.toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
    }

    @CacheEvict(value = "tasks", key = "#id")
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }


}
