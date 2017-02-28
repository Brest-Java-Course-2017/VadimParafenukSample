DROP TABLE IF EXISTS student_groups;
CREATE TABLE student_groups (
  group_id   INT          NOT NULL AUTO_INCREMENT,
  name       VARCHAR(255) NOT NULL,
  speciality VARCHAR(255) NOT NULL,
  PRIMARY KEY (group_id)
);

DROP TABLE IF EXISTS students;
CREATE TABLE students (
  student_id INT          NOT NULL AUTO_INCREMENT,
  name       VARCHAR(255) NOT NULL,
  gpa        FLOAT        NOT NULL,
  PRIMARY KEY (student_id)
);

DROP TABLE IF EXISTS students_in_groups;
CREATE TABLE students_in_groups (
  student_id INT  NOT NULL,
  group_id   INT  NOT NULL,
  PRIMARY KEY (student_id, group_id),
  FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
  FOREIGN KEY (group_id) REFERENCES student_groups(group_id) ON DELETE CASCADE
);