package com.example.rhsystem.Class.SecClass;

public class Course {
    private String courseId;
    private String courseName;
    private String collegeName;
    private String teacherId;
    private String teacherName;
    private String status;//选修必修
    private int credit;//学分
    private int capacity;//总容量
    private int stuNum;//当前容量

    public Course(String courseId, String courseName, String collegeName, String teacherId, String teacherName, String status, int credit, int capacity, int stuNum)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.collegeName = collegeName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.status = status;
        this.credit = credit;
        this.capacity = capacity;
        this.stuNum = stuNum;
    }

    public Course() { }

    public String GetCourseId() {
        return courseId;
    }

    public void SetCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String GetCourseName() {
        return courseName;
    }

    public void SetCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String GetCollegeName() { return collegeName; }

    public void SetCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String GetTeacherId() {
        return teacherId;
    }

    public void SetTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String GetTeacherName() { return teacherName; }

    public void SetTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String GetStatus() {
        return status;
    }

    public void SetStatus(String status) {
        this.status = status;
    }

    public int GetCredit() { return credit; }

    public void SetCredit(int credit) {
        this.credit = credit;
    }

    public int GetCapacity() {
        return capacity;
    }

    public void SetCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int GetStuNum() {
        return stuNum;
    }

    public void SetStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

//    public String GetStudents() {return students; }
//
//    public void SetStudents(String students) { this.students = students; }


}