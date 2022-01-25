package com.chatapp.chatappbackend.rdb.repositories;

import com.chatapp.chatappbackend.rdb.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository<ProjectEntity, String> {

    Optional<ProjectEntity> findByApiKey(String apiKey);

}
