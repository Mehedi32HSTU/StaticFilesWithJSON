package com.spring.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.model.FileDetails;

@Repository
public interface FileDetailsRepository extends JpaRepository<FileDetails, Long> {

}
