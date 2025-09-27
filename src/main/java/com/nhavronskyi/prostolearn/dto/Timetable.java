package com.nhavronskyi.prostolearn.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Data
@Entity(name = "timetable")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalTime startTime;
    private LocalTime endTime;

    private Set<DayOfWeek> workingDays;

    @OneToOne(mappedBy = "timetable")
    private Teacher teacher;

    public boolean isInWorkingDays(Lesson lesson) {
        return workingDays.contains(lesson.getStartTime().getDayOfWeek()) && workingDays.contains(lesson.getEndTime().getDayOfWeek());
    }

    public boolean isInWorkingHours(Lesson lesson) {
        return lesson.getStartTime().toLocalTime().isAfter(startTime.minusMinutes(1)) && lesson.getEndTime().toLocalTime().isBefore(endTime.plusMinutes(1));
    }
}
