package com.epam;

import java.util.List;

public class Group {

    private Integer groupId;
    private String name;
    private String speciality;

    public Group() {
    }

    public Group(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public Group(Integer groupId, String name, String speciality) {
        this.groupId = groupId;
        this.name = name;
        this.speciality = speciality;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (groupId != null ? !groupId.equals(group.groupId) : group.groupId != null) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        return speciality != null ? speciality.equals(group.speciality) : group.speciality == null;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}