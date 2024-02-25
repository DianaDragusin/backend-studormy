CREATE TABLE IF NOT EXISTS roomates_group (
    group_id        INTEGER AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS dormitory (
     dormitory_id        INTEGER AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS university (
    university_id   INTEGER AUTO_INCREMENT PRIMARY KEY,
    university_name            VARCHAR(255)
    );
CREATE TABLE IF NOT EXISTS student (
   student_id          INTEGER AUTO_INCREMENT PRIMARY KEY,
    firstname           VARCHAR(50),
    lastname            VARCHAR(50),
    birthday            DATE,
    registration_number INTEGER,
    university_id       INTEGER,
    dormitory_id        INTEGER,
    agreableness        INTEGER,
    open_to_experience  INTEGER,
    neuroticism         INTEGER,
    concienciousness    INTEGER,
    extraversion        INTEGER,
    group_id            INTEGER,
    FOREIGN KEY (group_id) REFERENCES roomates_group (group_id),
    FOREIGN KEY (university_id) REFERENCES university (university_id),
    FOREIGN KEY (dormitory_id) REFERENCES dormitory (dormitory_id)
    );


CREATE TABLE IF NOT EXISTS language (
    language_id     INTEGER AUTO_INCREMENT PRIMARY KEY,
    language_name            VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS question (
     question_id     INTEGER AUTO_INCREMENT PRIMARY KEY,
     text            VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS room (
     room_id             INTEGER AUTO_INCREMENT PRIMARY KEY,
     room_number         INTEGER,
     max_people_number   INTEGER,
     group_id            INTEGER,
     dormitory_id        INTEGER,
     FOREIGN KEY (group_id) REFERENCES roomates_group(group_id),
     FOREIGN KEY (dormitory_id) REFERENCES dormitory(dormitory_id)
);

CREATE TABLE IF NOT EXISTS scores (
    student_first_id    INTEGER,
    student_second_id   INTEGER,
    score               INTEGER,
    PRIMARY KEY (student_first_id, student_second_id),
    FOREIGN KEY (student_first_id) REFERENCES student (student_id),
    FOREIGN KEY (student_second_id) REFERENCES student (student_id)
    );

CREATE TABLE IF NOT EXISTS specialization (
     specialization_id   INTEGER AUTO_INCREMENT PRIMARY KEY,
     name                VARCHAR(255)
    );
CREATE TABLE IF NOT EXISTS university_specialization (
    university_id       INTEGER,
    specialization_id   INTEGER,
    PRIMARY KEY (university_id, specialization_id),
    FOREIGN KEY (university_id) REFERENCES university (university_id),
    FOREIGN KEY (specialization_id) REFERENCES specialization (specialization_id)
    );


CREATE TABLE IF NOT EXISTS specialization_language (
    specialization_id   INTEGER,
    language_id         INTEGER,
    PRIMARY KEY (specialization_id, language_id),
    FOREIGN KEY (specialization_id) REFERENCES specialization (specialization_id),
    FOREIGN KEY (language_id) REFERENCES language (language_id)
    );



CREATE TABLE IF NOT EXISTS answer (
     student_id      INTEGER,
     question_id     INTEGER,
     response        INTEGER,
    PRIMARY KEY (student_id, question_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id)
    );
