package com.community.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.community.dao.StudentDao;
import com.community.exception.ResourceNotFoundException;
import com.community.model.Student;
import com.community.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	//inject student DAO 
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public Student createStudent( Student student) {

            
            return studentDao.save(student);
        
	}

	//get all student by
	@Override
	public List<Student> getAllStudent() {

		List<Student> students =studentDao.findAll();
				
		if (!students.isEmpty()) {
	        return students;
	    } else {
	        throw new ResourceNotFoundException("No students found");
	    }		
	}

	
	//get student by id
	@Override
	public ResponseEntity<Student> getStudentById(int id) {
		Student student = studentDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found any record for this ID"+id));
		try {

			if (student != null)
			{
				return new ResponseEntity<>(student, HttpStatus.OK);

			} else 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	//delete all students from database
	
	@Override
	public ResponseEntity<String> deleteAll() {
	    try {
	        studentDao.deleteAll();
	        return new ResponseEntity<>("All records deleted", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error deleting records: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	
	//update student by id
	@Override
	public ResponseEntity<Student> updateById(int id, Student student) {

		Student foundedStudent = studentDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID "+id));

		foundedStudent.setFirstName(student.getFirstName());
		foundedStudent.setLastName(student.getLastName());
		foundedStudent.setAddress(student.getAddress());
		foundedStudent.setEmail(student.getEmail());

		studentDao.save(foundedStudent);
		return ResponseEntity.ok(foundedStudent);

	}

	@Override
	public ResponseEntity<String> deleteById(int id) {
		
		 try {
		        // Check student with the given ID exists, or throw ResourceNotFoundException
		       studentDao.findById(id)
		                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID " + id));

		        // Delete student by ID
		        studentDao.deleteById(id);

		        // Return a success response
		        return ResponseEntity.ok("Student with ID " + id + " deleted successfully");
		    } catch (ResourceNotFoundException e) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		        }
	}




	

}
