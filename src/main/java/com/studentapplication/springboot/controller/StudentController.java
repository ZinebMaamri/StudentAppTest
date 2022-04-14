package com.studentapplication.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentapplication.springboot.exception.ResourceNotFoundException;
import com.studentapplication.springboot.model.Student;
import com.studentapplication.springboot.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	//get all students
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	// create student rest API
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
		return ResponseEntity.ok(student);
	}
	
	// update employee rest api
	    @CrossOrigin(origins = "http://localhost:4200")
		@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
			
			student.setFirstName(studentDetails.getFirstName());
			student.setLastName(studentDetails.getLastName());
			student.setEmailId(studentDetails.getEmailId());
			student.setAge(studentDetails.getAge());
			
			Student updatedStudent = studentRepository.save(student);
			return ResponseEntity.ok(updatedStudent);
		}
	    
	 // delete employee rest api
	    @CrossOrigin(origins = "http://localhost:4200")
		@DeleteMapping("/students/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
			
			studentRepository.delete(student);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	    
	    
	
	  @CrossOrigin(origins = "http://localhost:4200")
	  @GetMapping("/studentscondition") public List<Student>
	  getAllStudentsByAge() { 
	    return  studentRepository.findAllByAge(); 
	  }
	 

}
