package com.sherbrookeuniversity.service;

import com.sherbrookeuniversity.entity.Role;
import com.sherbrookeuniversity.entity.Teacher;
import com.sherbrookeuniversity.exception.EmailAlreadyExistsException;
import com.sherbrookeuniversity.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher saveTeacher(Teacher teacher) {
        if (teacher.getId() == null) {

            // ✅ Vérification d'unicité de l'email
            if (teacherRepository.existsByEmail(teacher.getEmail())) {
                throw new EmailAlreadyExistsException("Email déjà utilisé");
            }

            // ✅ Rôle
            teacher.setRole(Role.TEACHER);

            // ✅ PAS DE HASH, PAS DE SÉCURITÉ
            teacher.setPassword(teacher.getPassword());

            // ✅ Statut initial
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
