package com.example.rhsystem.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/*
 *数据库帮助类，固定的写法
 */

public class MyDatabaseHelper extends SQLiteOpenHelper{
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);
        //调用父类的构造函数
    }

    /*
     *创建表，在首次运行程序时调用
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*
         *创建登录表
         * account 账号 字符类型，最大长度20 主键 唯一 不为空
         * password 姓名 字符类型，最大长度20 不为空
         * identity 数字型 不为空
         * 0：管理员
         * 1：学生
         * 2：老师
         */

        String UserTable = "CREATE TABLE UserTable (\n" +
                "    account         VARCHAR (200) PRIMARY KEY\n" +
                "                                  UNIQUE\n" +
                "                                  NOT NULL,\n" +
                "    password        VARCHAR (200) NOT NULL,\n" +
                "    identity        INTEGER       NOT NULL\n" +
                ");";


        /*
         *创建学生表
         * studentId 学号 字符类型，最大长度20 主键 唯一 不为空
         * studentName 姓名 字符类型，最大长度20 不为空
         * collegeName 学院 字符类型，最大长度20 不为空
         * groupId 班级 字符类型，最大长度20 不为空（group是sql语句的关键字，所以没有命名为group）
         * major 专业 字符类型，最大长度20 不为空
         * credit 学分 字符类型，最大长度20 不为空
         * birthday 生日 字符类型，最大长度20 不为空
         */
        String StudentTable="CREATE TABLE StudentTable (\n" +
                "    studentId       VARCHAR (200) PRIMARY KEY\n" +
                "                                  UNIQUE\n" +
                "                                  NOT NULL,\n" +
                "    studentName     VARCHAR (200) NOT NULL,\n" +
                "    collegeName     VARCHAR (200) NOT NULL,\n" +
                "    groupId         VARCHAR (200) NOT NULL,\n" +
                "    major           VARCHAR (200) NOT NULL,\n" +
                "    credit          VARCHAR (200) NOT NULL,\n" +
                "    birthday        VARCHAR (200) NOT NULL\n" +
                ");";


        /*
         *创建课程表
         * courseId 课程号 字符类型，最大长度20 主键 唯一 不为空
         * courseName 课程名 字符类型，最大长度20 不为空
         * collegeName 课程名 字符类型，最大长度20 不为空
         * teacherId  教师工号 字符类型，最大长度20 不为空
         * techerName  教师名 字符类型，最大长度20 不为空
         * status  选修必修 字符类型，最大长度20 不为空
         * credit 学分 符点型  不为空
         * capacity 总容量 整点型 不为空
         * stuNum 当前容量 整点型 不为空
         */


        String CourseTable="CREATE TABLE CourseTable (\n" +
                "    courseId        VARCHAR (200)  PRIMARY KEY\n" +
                "                                   UNIQUE\n" +
                "                                   NOT NULL,\n" +
                "    courseName      VARCHAR (200)  NOT NULL,\n" +
                "    collegeName     VARCHAR (200)  NOT NULL,\n" +
                "    teacherId       VARCHAR (200)  NOT NULL,\n" +
                "    teacherName     VARCHAR (200)  NOT NULL,\n" +
                "    status          VARCHAR (200)  NOT NULL,\n" +
                "    credit          INTEGER        NOT NULL,\n" +
                "    capacity        INTEGER        NOT NULL,\n" +
                "    stuNum          INTEGER        NOT NULL\n" +
                ");";
        /*
         *创建选课表
         *  studentId 学号 字符类型，最大长度20 不为空
         *  courseId 书号 字符类型，最大长度20 不为空
         *  grade 成绩 int类型
         *  使用studentId 和 Id 作为聚合主键
         */


        String ChooseCourseTable="CREATE TABLE ChooseCourseTable (\n" +
                "    studentId       VARCHAR (200)  NOT NULL,\n" +
                "    courseId        VARCHAR (200)  NOT NULL,\n" +
                "    grade           INTEGER                ,\n" +
                "    PRIMARY KEY (\n" +
                "        studentId,\n" +
                "        courseId\n" +
                "    )\n" +
                ");";

        sqLiteDatabase.execSQL(UserTable);
        sqLiteDatabase.execSQL(StudentTable);
        sqLiteDatabase.execSQL(CourseTable);
        sqLiteDatabase.execSQL(ChooseCourseTable );
        //执行建表语句
    }

    /*
     *数据库版本更新时调用，这里什么都不做
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {}
}