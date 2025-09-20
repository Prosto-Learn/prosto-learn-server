package com.nhavronskyi.prostolearn.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    public boolean isOverlapped(Lesson lesson) {
        return (lesson.getStartTime().isBefore(startTime) && lesson.getEndTime().isBefore(startTime.plusMinutes(1))) ||
               (lesson.getStartTime().isAfter(endTime.minusMinutes(1)) && lesson.getEndTime().isAfter(endTime));
    }

    @JsonIgnore
    public boolean isTimeValid() {
        return startTime.isBefore(endTime) && startTime.isAfter(LocalDateTime.now());
    }
}
