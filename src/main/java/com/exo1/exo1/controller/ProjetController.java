package com.exo1.exo1.controller;

import com.exo1.exo1.dto.ProjetDto;
import com.exo1.exo1.dto.UserDto;
import com.exo1.exo1.service.ProjetService;
import com.exo1.exo1.service.TaskPerProjectService;
import com.exo1.exo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projets")
public class ProjetController {
    @Autowired
    private ProjetService projetService;

    @Autowired
    private TaskPerProjectService taskPerProjectService;

    @GetMapping
    public ResponseEntity<List<ProjetDto>> findAll()
    {
        return ResponseEntity.ok(projetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDto> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(projetService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProjetDto> save(@RequestBody ProjetDto projetDto) {
        taskPerProjectService.refreshMaterializedView();
        return ResponseEntity.ok(projetService.save(projetDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetDto> update(@PathVariable Long id, @RequestBody ProjetDto projetDto) {
        taskPerProjectService.refreshMaterializedView();
        return ResponseEntity.ok(projetService.update(id, projetDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projetService.delete(id);
        taskPerProjectService.refreshMaterializedView();
        return ResponseEntity.noContent().build();
    }
}
