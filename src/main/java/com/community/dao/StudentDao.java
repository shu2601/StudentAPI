package com.community.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.community.model.Student;

public interface StudentDao extends JpaRepository<Student, Integer>{

	
	
	
}
