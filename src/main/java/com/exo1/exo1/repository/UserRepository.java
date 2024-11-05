package com.exo1.exo1.repository;

import com.exo1.exo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.task left join fetch u.projets WHERE u.id = :id")
    Optional<User> findUserById(@Param("id") Long id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.task WHERE u.id = :id")
    Optional<User> findByIdWithTask(@Param("id") Long id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.projets WHERE u.id = :id")
    Optional<User> findByIdWithProject(@Param("id") Long id);

    @Query("SELECT u FROM User u left join fetch u.task left join fetch u.projets")
    List<User> findAllUsers();
}
