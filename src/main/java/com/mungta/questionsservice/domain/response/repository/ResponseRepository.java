package com.mungta.questionsservice.domain.response.repository;

import com.mungta.questionsservice.domain.response.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
