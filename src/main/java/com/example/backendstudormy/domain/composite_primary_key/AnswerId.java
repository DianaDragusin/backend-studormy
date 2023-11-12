package com.example.backendstudormy.domain.composite_primary_key;

import com.example.backendstudormy.domain.entities.Question;
import com.example.backendstudormy.domain.entities.Student;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AnswerId implements Serializable {

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

}
