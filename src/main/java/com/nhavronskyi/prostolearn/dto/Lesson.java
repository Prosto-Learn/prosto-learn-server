package com.nhavronskyi.prostolearn.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "lessons")
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    private Long id;
    private String name;
    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Teacher teacher;
}
