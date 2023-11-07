package domain.entities;

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
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer studentId;

    @Column(name = "firstname")
    private String firstname;

    @Column(name="birthday")
    private String birthday;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "registration_number")
    private Integer registrationNumer;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "university_id")
    private University university;

    @Column(name = "agreableness")
    private Integer agreableness;

    @Column(name = "open_to_experience")
    private Integer openness ;

    @Column(name = "neuroticism")
    private Integer neuroticism ;

    @Column(name = "concienciousness")
    private Integer concienciousness ;

    @Column(name = "extraversion")
    private Integer extraversion ;


}
