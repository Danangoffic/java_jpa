package com.example.demo.student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with id " + studentId + " not exist");
        }
        studentRepository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentId, String email, String name) {
        Student student1 = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student id does not exist"));

        if(!name.isEmpty() || !name.equals("") || !student1.getName().equals(name)){
            student1.setName(name);
        }

        if(!email.isEmpty() || !email.equals("") || !student1.getEmail().equals(email)){
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if(studentByEmail.isPresent()){
                throw new IllegalStateException("Student with email : " + email + " is exist");
            }
            student1.setEmail(email);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, Map studentRequest) {
        Student student1 = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student id does not exist"));
        if(!studentRequest.containsKey("name")
                || !studentRequest.get("name").equals("")
                || !studentRequest.get("name").equals(null)){
            student1.setName((String) studentRequest.get("name"));
        }
        if(!studentRequest.containsKey("email") || !studentRequest.get("email").equals("") || !studentRequest.get("email").equals(null)){
            Optional<Student> emailData = studentRepository.findStudentByEmail((String) studentRequest.get("email"));
            if(emailData.isPresent()){
                throw new IllegalStateException("Email with " + emailData.get().getEmail() + " is taken");
            }
            student1.setEmail((String) studentRequest.get("email"));

        }
    }
}
