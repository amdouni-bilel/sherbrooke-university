package com.sherbrookeuniversity.service;

import com.sherbrookeuniversity.entity.Role;
import com.sherbrookeuniversity.entity.Student;
import com.sherbrookeuniversity.entity.User;
import com.sherbrookeuniversity.exception.EmailAlreadyExistsException;
import com.sherbrookeuniversity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student saveStudent(Student student) {
        if (student.getId() == null) {
            // Vérification d'unicité de l'email
            if (studentRepository.existsByEmail(student.getEmail())) {
                throw new EmailAlreadyExistsException("Email déjà utilisé");
            }
            student.setValidated(false);
            student.setRole(Role.STUDENT);
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            student.setStatus(User.Status.PENDING); // statut initial en attente
        }
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student student) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student validateStudent(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setValidated(true);
            return studentRepository.save(student);
        }
        return null;
    }
}
