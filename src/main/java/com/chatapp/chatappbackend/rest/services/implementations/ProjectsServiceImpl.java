package com.chatapp.chatappbackend.rest.services.implementations;

import com.chatapp.chatappbackend.rdb.entities.ProjectEntity;
import com.chatapp.chatappbackend.rdb.repositories.ProjectsRepository;
import com.chatapp.chatappbackend.rest.services.interfaces.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectsServiceImpl implements ProjectsService {

    private final ProjectsRepository projectsRepository;

    @Override
    public ProjectEntity getByApiKey(String apiKey) throws Exception {
        return projectsRepository.findByApiKey(apiKey)
                .orElseThrow(() -> new Exception("Project with apiKey: " + apiKey + " not found!"));
    }
}
