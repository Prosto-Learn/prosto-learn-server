package com.nhavronskyi.prostolearn.repository;

import com.nhavronskyi.prostolearn.dto.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
