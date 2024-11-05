package com.exo1.exo1.repository;

import com.exo1.exo1.entity.Task;
import com.exo1.exo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.user LEFT JOIN FETCH t.projet WHERE t.id = :id")
    Optional<Task> findTaskById(@Param("id") Long id);

    @Query("SELECT t FROM Task t left join fetch t.projet left join fetch t.user")
    List<Task> findAllTasks();
}
