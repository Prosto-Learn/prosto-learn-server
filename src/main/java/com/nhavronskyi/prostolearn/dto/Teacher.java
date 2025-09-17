package com.nhavronskyi.prostolearn.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "teachers")
    private List<Lesson> lessons;
}
