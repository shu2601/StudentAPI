package com.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.model.Student;
import com.community.service.StudentService;

import jakarta.validation.Valid;

@RestController
//@RequestMapping("/student")
public class StudentController {

	
	//Inject student service interface in controller and create obj
	@Autowired
	private StudentService studentService;
		
	
	//to create student in database
	@PostMapping("/student") 
	public ResponseEntity<?> createStudent(@Valid @RequestBody Student student, BindingResult result) {
		
		 if (result.hasErrors()) {
	            result.getAllErrors().forEach(error -> {
	                System.out.println("Validation Error: " + error.getDefaultMessage());
	            });
	            return ResponseEntity.badRequest().body("Validation error");
	        }
		
		return ResponseEntity.ok(this.studentService.createStudent(student));
	}
	
	
	//handler for get all students in the database
	@GetMapping("/getStudents")
	public List<Student> getAllStudents(){
		
		return this.studentService.getAllStudent();
	}
	
	//get single student information by id
	@GetMapping("/getStudents/{id}")
	public ResponseEntity <Student> getStudentById(@PathVariable String id) {
		
		return this.studentService.getStudentById(Integer.parseInt(id));
	}
	
	
	
	//update by id
	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<Student>updateById(@PathVariable String id, @RequestBody Student student) {
		
		
		return this.studentService.updateById(Integer.parseInt(id), student);
	}
	
	
	//delete all students
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll(){
		
		return this.studentService.deleteAll();
	}
	
	//delete all students
		@DeleteMapping("/deleteAll/{id}")
		public ResponseEntity<String> deleteById(@PathVariable String id){
			
			return this.studentService.deleteById(Integer.parseInt(id));
		}
	
	
	
	
	
	
}
