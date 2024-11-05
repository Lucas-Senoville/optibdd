package com.exo1.exo1.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task_per_project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskPerProject {
    @Id
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "task_count")
    private Long tasksCount;
}
