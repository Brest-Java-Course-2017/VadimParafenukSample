DROP TABLE IF EXISTS groups;
CREATE TABLE groups (
  group_id   INT          NOT NULL AUTO_INCREMENT,
  name       VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (group_id)
);

DROP TABLE IF EXISTS students;
CREATE TABLE students (
  student_id INT          NOT NULL AUTO_INCREMENT,
  name       VARCHAR(255) NOT NULL,
  gpa        FLOAT        NOT NULL,
  group_id   INT          NOT NULL,
  PRIMARY KEY (student_id),
  FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE
);