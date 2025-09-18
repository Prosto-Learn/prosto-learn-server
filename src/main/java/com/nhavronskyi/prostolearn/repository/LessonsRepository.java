package com.nhavronskyi.prostolearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nhavronskyi.prostolearn.dto.Lesson;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {

}
