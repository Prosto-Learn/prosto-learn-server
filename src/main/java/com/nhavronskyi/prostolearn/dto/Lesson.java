package com.nhavronskyi.prostolearn.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity(name = "lessons")
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Student student;

    public boolean isOverlapped(Lesson lesson) {
        return (lesson.getStartTime().isBefore(startTime) && lesson.getEndTime().isBefore(startTime.plusMinutes(1))) ||
               (lesson.getStartTime().isAfter(endTime.minusMinutes(1)) && lesson.getEndTime().isAfter(endTime));
    }

    @JsonIgnore
    public boolean isTimeValid() {
        return startTime.isBefore(endTime) && startTime.isAfter(LocalDateTime.now());
    }
}
