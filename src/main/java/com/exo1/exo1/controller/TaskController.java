package com.exo1.exo1.controller;

import com.exo1.exo1.dto.TaskDto;
import com.exo1.exo1.service.TaskPerProjectService;
import com.exo1.exo1.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@EnableCaching
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskPerProjectService taskPerProjectService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAll()
    {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDto> save(@RequestBody TaskDto etudiantDto) {
        taskPerProjectService.refreshMaterializedView();
        return ResponseEntity.ok(taskService.save(etudiantDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        taskPerProjectService.refreshMaterializedView();
        return ResponseEntity.ok(taskService.update(id, taskDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        taskPerProjectService.refreshMaterializedView();
        return ResponseEntity.noContent().build();
    }

}
