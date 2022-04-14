package com.studentapplication.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.studentapplication.springboot.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	  @Query(value="select s from Student s where s.age >= 22 ORDER BY s.id DESC ")
	  public List<Student> findAllByAge();
}

