package com.nhavronskyi.prostolearn.repository;

import com.nhavronskyi.prostolearn.dto.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
