package com.chatapp.chatappbackend.rest.services.interfaces;

import com.chatapp.chatappbackend.rdb.entities.CompanyEntity;

public interface CompaniesService {

    CompanyEntity getByApiKey(String apiKey) throws Exception;

    CompanyEntity getById(String id);

    CompanyEntity getByUsername();

}
