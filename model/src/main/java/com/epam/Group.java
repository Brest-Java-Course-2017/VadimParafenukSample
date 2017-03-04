package com.epam;

import java.util.Date;

public class Group {

    private Integer groupId;
    private String name;
    private Date graduationDate;

    public Group() {
    }

    public Group(String name, Date graduationDate) {
        this.name = name;
        this.graduationDate = graduationDate;
    }

    public Group(Integer groupId, String name, Date graduationDate) {
        this.groupId = groupId;
        this.name = name;
        this.graduationDate = graduationDate;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (groupId != null ? !groupId.equals(group.groupId) : group.groupId != null) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        return graduationDate != null ? graduationDate.equals(group.graduationDate) : group.graduationDate == null;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (graduationDate != null ? graduationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", graduationDate=" + graduationDate +
                '}';
    }
}