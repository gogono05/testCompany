package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.model.Dept;

public interface DeptRepository extends JpaRepository<Dept, Long> ,JpaSpecificationExecutor<Dept>{

}
