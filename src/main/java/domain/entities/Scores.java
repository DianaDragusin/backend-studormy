package domain.entities;

import domain.composite_primary_key.ScoreId;
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
@Table(name = "scores")
public class Scores {
    @EmbeddedId
    private ScoreId scoreId;
    @Column(name = "score")
    private Integer score;
}
