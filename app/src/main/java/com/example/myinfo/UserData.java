package com.example.myinfo;

public class UserData {
    private String firstName;
    private String lastName;

    private String fatherName;

    private String schoolName;
    private String collegeName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getCollegeName() {
        return collegeName;
    }
}
