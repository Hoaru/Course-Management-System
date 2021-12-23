package com.example.rhsystem.Class.SecClass;

public class Student{
    private String studentId;//学号
    private String studentName;
    private String collegeName;
    private String groupId;
    private String major;
    private String credit;//绩点
    private String birthday;
    //    vector<grade*>* grades = new vector<grade*>;


    public Student(String studentId, String studentName, String collegeName, String groupId, String major, String credit, String birthday) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.collegeName = collegeName;
        this.groupId = groupId;
        this.major = major;
        this.credit = credit;
        this.birthday = birthday;
    }

    public Student() { }

    public String GetStudentId() { return studentId; }

    public void SetStudentId(String studentId) { this.studentId = studentId; }

    public String GetStudentName() {return studentName; }

    public void SetStudentName(String studentName) { this.studentName = studentName; }

    public String GetCollegeName() {
        return collegeName;
    }

    public void SetCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String GetGroupId() {
        return groupId;
    }

    public void SetGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String GetMajor() { return major; }

    public void SetMajor(String major) {
        this.major = major;
    }

    public String GetCredit() {
        return credit;
    }

    public void SetCredit(String credit) {
        this.credit = credit;
    }

    public String GetBirthday() {
        return birthday;
    }

    public void SetBirthday(String birthday) {
        this.birthday = birthday;
    }

    //    public String GetGrades() {
    //        return grades;
    //    }
    //
    //    public void SetGrades(String grades) {
    //        this.grades = grades;
    //    }
}