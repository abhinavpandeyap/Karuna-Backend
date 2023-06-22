package com.app.karuna.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.karuna.entity.Request;

public interface RequestRepo extends JpaRepository<Request, Long> {

	List<Request> findAllByStatusFalse();

}
