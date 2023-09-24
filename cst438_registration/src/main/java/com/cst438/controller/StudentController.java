package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;
import com.cst438.service.GradebookService;

@RestController
@CrossOrigin
public class StudentController {
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Autowired
	GradebookService gradebookService;
	
	@PostMapping("/student/add")
	@Transactional
	public StudentDTO addStudent( @RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("status_code") int status_code, @RequestParam("status") String status) {
		StudentDTO newStudent = new StudentDTO(name, email, status_code, status);
		return newStudent;
	}
	
	// TODO: Warn before deleting
	@DeleteMapping("/student/{student_email}")
	@Transactional
	public void dropStudent(@PathVariable String student_email, @RequestParam("force") boolean forceDelete) {
		Student student = studentRepository.findByEmail(student_email);
		
		if (student != null && student.getEmail().equals(student_email)) {
			Iterable<Course> courses = courseRepository.findAll();
			for (Course course: courses) {
				Enrollment enrollment = enrollmentRepository.findByEmailAndCourseId(student_email, course.getCourse_id());
				if (enrollment != null) {
					if (!forceDelete) {
						System.out.println("Enrollment will be deleted: " + enrollment.getEnrollment_id());
					}
					enrollmentRepository.delete(enrollment);
				}
			}
			studentRepository.delete(student);
		}
	}
	
	@GetMapping("/student")
	public Student getStudent(@RequestParam("student_email") String student_email, @RequestParam("first_name") String first_name) {
		Student student = studentRepository.findByEmail(student_email);
		if (student != null) {
			return student;
		}
		throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student email not valid: "+student_email);
	}
	
	@PostMapping("/student/edit")
	public StudentDTO editStudent(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("status_code") int status_code, @RequestParam("status") String status) {
		Student student = studentRepository.findByEmail(email);
		if (student != null) {
			student.setName(name);
			student.setStatus(status);
			student.setStatusCode(status_code);
			StudentDTO studentDTO = new StudentDTO(name, email, status_code, status);
			return studentDTO;
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student email not valid: " + email);
	}
	
	@GetMapping("/student/all")
	public Iterable<Student> getAllStudents() {
		Iterable<Student> students = studentRepository.findAll();
		return students;
	}
}
