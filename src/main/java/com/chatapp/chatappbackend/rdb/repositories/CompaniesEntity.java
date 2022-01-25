package com.chatapp.chatappbackend.rdb.repositories;

import com.chatapp.chatappbackend.rdb.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniesEntity extends JpaRepository<CompanyEntity, String> {}
