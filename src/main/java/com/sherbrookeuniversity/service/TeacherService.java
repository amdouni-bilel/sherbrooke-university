package com.sherbrookeuniversity.service;

import com.sherbrookeuniversity.entity.Role;
import com.sherbrookeuniversity.entity.Teacher;
import com.sherbrookeuniversity.exception.EmailAlreadyExistsException;
import com.sherbrookeuniversity.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Teacher saveTeacher(Teacher teacher) {
        if (teacher.getId() == null) {
            // Vérification d'unicité de l'email
            if (teacherRepository.existsByEmail(teacher.getEmail())) {
                throw new EmailAlreadyExistsException("Email déjà utilisé");
            }
            // Définir le rôle
            teacher.setRole(Role.TEACHER);
            // Hasher le mot de passe
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            // Statut initial
            teacher.setStatus(com.sherbrookeuniversity.entity.User.Status.ACTIVE);
        }
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        if (teacherRepository.existsById(id)) {
            teacher.setId(id);
            return teacherRepository.save(teacher);
        }
        return null;
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
