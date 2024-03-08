CREATE TABLE IF NOT EXISTS roomates_group (
    group_id        INTEGER AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS dormitory (
     dormitory_id        INTEGER AUTO_INCREMENT PRIMARY KEY
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
    FOREIGN KEY (dormitory_id) REFERENCES dormitory (dormitory_id)
    );



CREATE TABLE IF NOT EXISTS question (
     question_id     INTEGER AUTO_INCREMENT PRIMARY KEY,
     text            VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS room (
     room_id             INTEGER AUTO_INCREMENT PRIMARY KEY,
     room_number         INTEGER,
     max_people_number   INTEGER,
     dormitory_id        INTEGER,
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



CREATE TABLE IF NOT EXISTS answer (
     student_id      INTEGER,
     question_id     INTEGER,
     response        INTEGER,
    PRIMARY KEY (student_id, question_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id)
    );
