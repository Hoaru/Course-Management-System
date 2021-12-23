package com.example.rhsystem.Class.StuClass;

public class ChooseCourse {
    private String StudentId;
    private String CourseId;

    public ChooseCourse(String StudentId, String CourseId)
    {
        this.StudentId = StudentId;
        this.CourseId = CourseId;
    }

    public ChooseCourse(){}

    public String GetStudentId() {return StudentId;}

    public void SetStudentId(String StudentId) {this.StudentId = StudentId;}

    public String GetCourseId() {return CourseId;}

    public void SetCourseId(String CourseId) {this.CourseId = CourseId;}
}
