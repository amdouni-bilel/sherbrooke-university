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
        // Définir le rôle par défaut
        teacher.setRole(Role.TEACHER);

        // Hasher le mot de passe si présent
      //  if (teacher.getPassword() != null && !teacher.getPassword().isEmpty()) {
     //       teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
    //    }

        // Statut par défaut
        teacher.setStatus(com.sherbrookeuniversity.entity.User.Status.ACTIVE);

        // Sauvegarde directe
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
