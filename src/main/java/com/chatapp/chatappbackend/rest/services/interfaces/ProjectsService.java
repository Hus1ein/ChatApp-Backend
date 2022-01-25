package com.chatapp.chatappbackend.rest.services.interfaces;

import com.chatapp.chatappbackend.rdb.entities.ProjectEntity;

public interface ProjectsService {

    ProjectEntity getByApiKey(String apiKey) throws Exception;

}
