package domain.composite_primary_key;

import domain.entities.Question;
import domain.entities.Student;
import jakarta.persistence.Column;
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
