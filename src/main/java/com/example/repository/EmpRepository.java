package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.model.Emp;

public interface EmpRepository extends JpaRepository<Emp, Long> , JpaSpecificationExecutor<Emp> {
	
}
