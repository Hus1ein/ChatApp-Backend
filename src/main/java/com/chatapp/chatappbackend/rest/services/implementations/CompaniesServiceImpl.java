package com.chatapp.chatappbackend.rest.services.implementations;

import com.chatapp.chatappbackend.rdb.entities.CompanyEntity;
import com.chatapp.chatappbackend.rest.services.interfaces.CompaniesService;
import com.chatapp.chatappbackend.rest.services.interfaces.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompaniesServiceImpl implements CompaniesService {

    private final ProjectsService projectsService;

    @Override
    public CompanyEntity getByApiKey(String apiKey) throws Exception {
        return projectsService.getByApiKey(apiKey).getCompany();
    }

    @Override
    public CompanyEntity getById(String id) {
        return null;
    }

    @Override
    public CompanyEntity getByUsername() {
        return null;
    }

}
