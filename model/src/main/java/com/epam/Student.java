package com.epam;

public class Student {

    private Integer studentId;
    private String name;
    private Float gpa; // Great Point Average
    private Group group;

    public Student() {
    }

    public Student(Integer studentId, String name, Float gpa) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = gpa;
    }

    public Student(String name, Float gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentId != null ? !studentId.equals(student.studentId) : student.studentId != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        return gpa != null ? gpa.equals(student.gpa) : student.gpa == null;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gpa != null ? gpa.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
