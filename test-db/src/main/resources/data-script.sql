INSERT INTO student_groups (group_id, name, speciality)
VALUES (1, 'II-12', 'AI'),
       (2, 'AS-41', 'ASOI'),
       (3, 'AS-42', 'ASOI');

INSERT INTO students (student_id, name, gpa)
VALUES (1, 'Vadim', 8.0),
       (2, 'Sergey', 9.8),
       (3, 'Vasiliy', 5.0),
       (4, 'Petr', 7.8),
       (5, 'Nikolay', 8.2),
       (6, 'Nastya', 6.2),
       (7, 'Roma', 7.0),
       (8, 'Alexandr', 9.2),
       (9, 'Akakiy', 10.0),
       (10, 'XXL Den4ik XXL', 11.0);

INSERT INTO students_in_groups (student_id, group_id)
VALUES  (1, 1),
        (2, 1),
        (3, 2),
        (4, 2),
        (5, 3),
        (6, 3),
        (7, 3),
        (8, 2),
        (9, 3),
        (10, 1);