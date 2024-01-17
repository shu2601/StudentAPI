package com.community.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.community.model.Student;

public interface StudentService {

	//create new student 
	Student createStudent(Student student);

	//get all students list 
	List<Student> getAllStudent();

	//return status code with which student get by ID
	ResponseEntity <Student> getStudentById(int id);

	//delete all students record exist in databse
	ResponseEntity<String> deleteAll();

	//delete student using id given from front end
	ResponseEntity<String> deleteById(int id);

	//update student and return msg with updated student
	ResponseEntity<Student> updateById(int id, Student student);

	


}
