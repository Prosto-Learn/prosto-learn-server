package com.nhavronskyi.prostolearn.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "teacher")
    @JsonManagedReference
    private List<Lesson> lessons;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher")
    private Timetable timetable;

    public static Teacher withId(Long id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacher;
    }
}
