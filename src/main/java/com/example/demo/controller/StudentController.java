package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
	
	private List<Student> students = new ArrayList<>(List.of(new Student(1, "Alice", 100), new Student(2, "Bob", 90), new Student(3, "Charlie", 80)));

	
	@GetMapping("/students")
	public List<Student> getStudents() {
        return students;
	}
	
	
	// Get a CSRF token
	//add this token to the header of the POST, PUT, DELETE request, otherwise, the server will return 401 unauthorized error.
	@GetMapping("/csrf-token")
	public CsrfToken csrf(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	// Add a new student
	//localhost:8080/students
	// { "id" : 4, "name" : "David", "score" : 90 }
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {
		students.add(student);
		return student;
	}

}
