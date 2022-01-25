package com.chatapp.chatappbackend.rdb.repositories;

import com.chatapp.chatappbackend.rdb.entities.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagesRepository extends JpaRepository<PackageEntity, String> {}
