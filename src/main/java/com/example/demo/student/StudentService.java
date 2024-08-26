package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    

    private final StudentRepository studentRepository;

    @Autowired
    StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
		return studentRepository.findAll();
	}

    public void addStudent(Student student) {
        Optional<Student> studentOptional = 
            studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email already present");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);
        if(!studentExists){
            throw new IllegalStateException("invalid id " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public Student updateStudent(Long studentId, String name, String email, String dob) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalStateException("invalid id " + studentId));
        if(name != null && name.length() > 0 && !Objects.equals(name, student.getName())){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email already present");
            }
            student.setEmail(email);
        }

        if(dob != null && dob.length() > 0 && !Objects.equals(LocalDate.parse(dob), student.getDob())){
            student.setDob(LocalDate.parse(dob));
        }

        return student;
    }
}
