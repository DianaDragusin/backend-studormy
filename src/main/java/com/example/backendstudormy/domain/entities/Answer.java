package com.example.backendstudormy.domain.entities;

import com.example.backendstudormy.domain.composite_primary_key.AnswerId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {
    @EmbeddedId
    private AnswerId answerId;

    @Column(name = "response")
    private Integer response;
}
