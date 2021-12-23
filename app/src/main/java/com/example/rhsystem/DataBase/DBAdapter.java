package com.example.rhsystem.DataBase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rhsystem.Class.SecClass.Course;
import com.example.rhsystem.Class.SecClass.Student;
import com.example.rhsystem.Class.User;

import java.util.ArrayList;
import java.util.HashMap;

public class DBAdapter {
    private static MyDatabaseHelper helper;

    //初始化
    public static void Init(Context context)
    {
        helper=new MyDatabaseHelper(context,"ManagementSystem.db",null,1);
        //创建数据库 helper 对象，传入上下文，数据库名称ManagementSystem.db 工厂为null 版本号为1
    }


    public static long InsertUser(User target)
    {
        SQLiteDatabase db=helper.getWritableDatabase();//打开数据库
        ContentValues values = new ContentValues();
        //绑定值用的对象
        // 使用ContentValues对象，将target 对象中的各属性和表中的各列绑定
        values.put("account",target.GetAccount());
        values.put("password",target.GetPassword());
        values.put("identity",target.GetIdentity());
        //绑定以后调用数据库中的insert 方法，第一个参数 是表名，第三个参数是绑定好的值。(不要问第二个参数是啥)
        long res= db.insert("UserTable", null, values);
        db.close();//关闭
        return res;
    }


    //根据编号查找登录信息 找到返回实例化对象，找不到返回null
    public static User SearchUserByAccount(String account)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("UserTable", new String[]{"account","password","identity"},
                "account=?", new String[]{account}, null, null, null);
        User res=null;
        while(cursor.moveToNext()) {
            res = new User(
                    cursor.getString(cursor.getColumnIndex("account")),
                    cursor.getString(cursor.getColumnIndex("password")),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("identity")))
            );
        }

        //此处同上
        db.close();
        return res;
    }


    //插入学生到数据库，target 学生对象 返回值 ：成功插入返回插入的idm失败返回-1
    public static long InsertStudent(Student target)
    {
        SQLiteDatabase db=helper.getWritableDatabase();//打开数据库
        ContentValues values = new ContentValues();
        //绑定值用的对象
        // 使用ContentValues对象，将target 对象中的各属性和表中的各列绑定
        values.put("studentId",target.GetStudentId());
        values.put("studentName",target.GetStudentName());
        values.put("collegeName",target.GetCollegeName());
        values.put("groupId",target.GetGroupId());
        values.put("major",target.GetMajor());
        values.put("credit",target.GetCredit());
        values.put("birthday",target.GetBirthday());
        //绑定以后调用数据库中的insert 方法，第一个参数 是表名，第三个参数是绑定好的值。(不要问第二个参数是啥)
        long res= db.insert("StudentTable", null, values);
        db.close();//关闭
        return res;
    }


    //获得所有学生，结构保存到ArrayList<Student>中，若没有学生，返回一个空的ArrayList集合
    public static ArrayList<HashMap<String, Object>> GetAllStudents()
    {
        SQLiteDatabase db=helper.getWritableDatabase();//打开数据库

        Cursor cursor = db.query("StudentTable", new String[]{"studentId","studentName","collegeName",
                        "groupId","major","credit","birthday"},
                null, null, null, null, null);
        ArrayList<HashMap<String, Object>> res=new ArrayList<HashMap<String, Object>>();
        //声明用于保存结果的集合

        while(cursor.moveToNext()){//如果还有表项就循环
            HashMap<String, Object> object=new HashMap<String, Object>();
            //声明用于保存临时对象
            object.put("studentId",cursor.getString(cursor.getColumnIndex("studentId")));
            object.put("studentName",cursor.getString(cursor.getColumnIndex("studentName")));
            object.put("collegeName",cursor.getString(cursor.getColumnIndex("collegeName")));
            object.put("groupId",cursor.getString(cursor.getColumnIndex("groupId")));
            object.put("major",cursor.getString(cursor.getColumnIndex("major")));
            object.put("credit",cursor.getString(cursor.getColumnIndex("credit")));
            object.put("birthday",cursor.getString(cursor.getColumnIndex("birthday")));
            //将查询到的列名的值与对象的属性值绑定
            res.add(object);
            //添加到集合中
        }
        db.close();
        return res;//返回集合
    }//获得所有学生，结构保存到ArrayList<Student>中，若没有学生，返回一个空的ArrayList集合


    public static ArrayList<HashMap<String, Object>> GetAStudent(String id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();//打开数据库

        Cursor cursor = db.query("StudentTable", new String[]{"studentId","studentName","collegeName",
                        "groupId","major","credit","birthday"},
                "studentId = ?", new String[]{id}, null, null, null);
        ArrayList<HashMap<String, Object>> res=new ArrayList<HashMap<String, Object>>();
        //声明用于保存结果的集合

        while(cursor.moveToNext()){//如果还有表项就循环
            HashMap<String, Object> object=new HashMap<String, Object>();
            //声明用于保存临时对象
            object.put("studentId",cursor.getString(cursor.getColumnIndex("studentId")));
            object.put("studentName",cursor.getString(cursor.getColumnIndex("studentName")));
            object.put("collegeName",cursor.getString(cursor.getColumnIndex("collegeName")));
            object.put("groupId",cursor.getString(cursor.getColumnIndex("groupId")));
            object.put("major",cursor.getString(cursor.getColumnIndex("major")));
            object.put("credit",cursor.getString(cursor.getColumnIndex("credit")));
            object.put("birthday",cursor.getString(cursor.getColumnIndex("birthday")));
            //将查询到的列名的值与对象的属性值绑定
            res.add(object);
            //添加到集合中
        }
        db.close();
        return res;//返回集合
    }


    //删除 学号为id的学生 同时删除了所有该学生的选课信息 成功返回1，失败返回0
    public static int DeleteStudentByStudentId(String id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        db.delete("ChooseCourseTable","studentId=?",new String[]{id});
        int res=db.delete("StudentTable","studentId=?",new String[]{id});
        db.close();
        return res;
    }


    //查找学号为 id的学生信息,成功返回学生对象,失败返回null
    public static Student SearchStudentByStudentId(String id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();

        Cursor cursor = db.query("StudentTable", new String[]{"studentId","studentName","collegeName",
                        "groupId","major","credit","birthday"},
                "studentId=?", new String[]{id}, null, null, null);
        Student res=null;
        if (cursor.getCount() !=0)//如果未找到则返回空值
        {
            while(cursor.moveToNext()) {
                res = new Student();
                res.SetStudentId(cursor.getString(cursor.getColumnIndex("studentId")));
                res.SetStudentName(cursor.getString(cursor.getColumnIndex("studentName")));
                res.SetCollegeName(cursor.getString(cursor.getColumnIndex("collegeName")));
                res.SetGroupId(cursor.getString(cursor.getColumnIndex("groupId")));
                res.SetMajor(cursor.getString(cursor.getColumnIndex("major")));
                res.SetCredit(cursor.getString(cursor.getColumnIndex("credit")));
                res.SetBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
            }
        }
        //此处同上
        db.close();
        return res;
    }


    //将数据库中书号为id的表项的值更新为 student的值
    public static int UpdateStudent(String id ,Student student)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("studentId",student.GetStudentId());
        values.put("studentName",student.GetStudentName());
        values.put("collegeName",student.GetCollegeName());
        values.put("groupId",student.GetGroupId());
        values.put("major",student.GetMajor());
        values.put("credit",student.GetCredit());
        values.put("birthday",student.GetBirthday());
        int res= db.update("StudentTable", values, "studentId = ?", new String[]{id});
        //后两个参数的含义同 query 方法
        db.close();
        return res;
    }

//-------------------------------------------------------------------------------------------------------------

    public static long InsertCourse(Course target)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //绑定值用的对象
        // 使用ContentValues对象，将Course 对象中的各属性和表中的各列绑定
        values.put("courseId",target.GetCourseId());
        values.put("courseName",target.GetCourseName());
        values.put("collegeName",target.GetCollegeName());
        values.put("teacherId",target.GetTeacherId());
        values.put("teacherName",target.GetTeacherName());
        values.put("status",target.GetStatus());
        values.put("credit",target.GetCredit());
        values.put("capacity",target.GetCapacity());
        values.put("stuNum",target.GetStuNum());

        //绑定以后调用数据库中的insert 方法，第一个参数 是表名，第三个参数是绑定好的值。
        long res = db.insert("CourseTable", null, values);
        db.close();
        return res;
    }


    //查找全部课程，同GetAllStudents
    public static ArrayList<HashMap<String, Object>> GetAllCourses()
    {
        SQLiteDatabase  db=helper.getWritableDatabase();
        Cursor cursor = db.query("CourseTable", new String[]{"courseId","courseName","collegeName",
                        "teacherId","teacherName","status","credit","capacity","stuNum"},
                null, null, null, null, null);
        ArrayList<HashMap<String, Object>> res=new ArrayList<HashMap<String, Object>>();
        //声明用于保存结果的集合

        while(cursor.moveToNext()){//如果还有表项就循环
            HashMap<String, Object> object=new HashMap<String, Object>();
            //声明用于保存临时对象
            object.put("courseId",cursor.getString(cursor.getColumnIndex("courseId")));
            object.put("courseName",cursor.getString(cursor.getColumnIndex("courseName")));
            object.put("collegeName",cursor.getString(cursor.getColumnIndex("collegeName")));
            object.put("teacherId",cursor.getString(cursor.getColumnIndex("teacherId")));
            object.put("teacherName",cursor.getString(cursor.getColumnIndex("teacherName")));
            object.put("status",cursor.getString(cursor.getColumnIndex("status")));
            object.put("credit",Integer.parseInt(cursor.getString(cursor.getColumnIndex("credit"))));
            object.put("capacity",Integer.parseInt(cursor.getString(cursor.getColumnIndex("capacity"))));
            object.put("stuNum",Integer.parseInt(cursor.getString(cursor.getColumnIndex("stuNum"))));
            //将查询到的列名的值与对象的属性值绑定
            res.add(object);
            //添加到集合中
        }
        db.close();
        return res;//返回集合
    }


    //根据课程号查找课程 同SearchStudentByStudentId
    public static Course SearchCourseByCourseId(String id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("CourseTable", new String[]{"courseId","courseName","collegeName",
                        "teacherId","teacherName","status","credit","capacity","stuNum"},
                "courseId=?", new String[]{id}, null, null, null);

        Course res=null;
        if (cursor.getCount()!=0)//如果未找到则返回空值
        {
            while(cursor.moveToNext()) {
                res = new Course();
                res.SetCourseId(cursor.getString(cursor.getColumnIndex("courseId")));
                res.SetCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                res.SetCollegeName(cursor.getString(cursor.getColumnIndex("collegeName")));
                res.SetTeacherId(cursor.getString(cursor.getColumnIndex("teacherId")));
                res.SetTeacherName(cursor.getString(cursor.getColumnIndex("teacherName")));
                res.SetStatus(cursor.getString(cursor.getColumnIndex("status")));
                res.SetCredit(Integer.parseInt(cursor.getString(cursor.getColumnIndex("credit"))));
                res.SetCapacity(Integer.parseInt(cursor.getString(cursor.getColumnIndex("capacity"))));
                res.SetStuNum(Integer.parseInt(cursor.getString(cursor.getColumnIndex("stuNum"))));
            }
        }
        //此处同上
        db.close();
        return res;
    }


    //删除课程，其他的操作放到活动中去做（只删除了课程，没有删除选课等操作）
    public static int DeleteCourseByCourseId(String id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        int res=db.delete("CourseTable","courseId=?",new String[]{id});
        db.close();
        return res;
    }


    //更新课程信息
    public static int UpdateCourse(String id ,Course course)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("courseId",course.GetCourseId());
        values.put("courseName",course.GetCourseName());
        values.put("collegeName",course.GetCollegeName());
        values.put("teacherId",course.GetTeacherId());
        values.put("teacherName",course.GetTeacherName());
        values.put("status",course.GetStatus());
        values.put("credit",course.GetCredit());
        values.put("capacity",course.GetCapacity());
        values.put("stuNum",course.GetStuNum());

        //以上代码将列名与属性绑定，原理同上
        int res = db.update("CourseTable", values, "courseId = ?", new String[]{id});
        db.close();
        return res;
    }


//-------------------------------------------------------------------------------------------------------------

    //插入课程信息,同时课程的现容量加一
    public static long InsertChooseCourse(Student student,Course course)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //绑定值用的对象
        // 使用ContentValues对象，将Student 对象中的各属性和表中的各列绑定
        values.put("studentId",student.GetStudentId());
        values.put("courseId",course.GetCourseId());
        values.put("grade",0);
        //绑定以后调用数据库中的insert 方法，第一个参数 是表名，第三个参数是绑定好的值。
        long res = db.insert("ChooseCourseTable", null, values);
        if(res==-1)
        {
            db.close();
            return res;
        }
        else//一定要先插入选课信息后更新课程信息，因为插入选课记录可能会失败。
        {
            db.close();
            if (course.GetCapacity() > course.GetStuNum())
            {
                course.SetStuNum(course.GetStuNum()+1);//设置已选人数加一
                UpdateCourse(course.GetCourseId(),course);//更新课程信息
                return 1;
            }
            else
                return 8;//人数已满
        }
    }


    //获取一个学生所有的选课信息，放回一个保存选课信息的集合
    public static ArrayList<HashMap<String, Object>> GetChooseCourseByStudentId(String id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("ChooseCourseTable", new String[]{"courseId"},
                "studentId=?",  new String[]{id}, null, null, null);
        ArrayList<HashMap<String, Object>> res=new ArrayList<HashMap<String, Object>>();
        //声明用于保存结果的集合

        while(cursor.moveToNext()){//如果还有表项就循环
            String now;
            Course course;
            HashMap<String, Object> object=new HashMap<String, Object>();
            //声明用于保存临时对象
            now=cursor.getString(cursor.getColumnIndex("courseId"));
            //将查询到的列名的值与对象的属性值绑定
            course = DBAdapter.SearchCourseByCourseId(now);
            object.put("courseId",course.GetCourseId());
            object.put("courseName",course.GetCourseName());
            object.put("collegeName",course.GetCollegeName());
            object.put("teacherId",course.GetTeacherId());
            object.put("teacherName",course.GetTeacherName());
            object.put("status",course.GetStatus());
            object.put("credit",course.GetCredit());
            object.put("capacity",course.GetCapacity());
            object.put("stuNum",course.GetStuNum());
            //将查询到的列名的值与对象的属性值绑定
            res.add(object);
            //添加到集合中
        }
        db.close();
        return res;//返回集合
    }


    //获取一个学生所有的选课信息，放回一个保存选课信息的集合
    public static ArrayList<HashMap<String, Object>> GetChooseCourseByCourseId(String id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("ChooseCourseTable", new String[]{"courseId","studentId","grade"},
                "courseId=?",  new String[]{id}, null, null, null);
        ArrayList<HashMap<String, Object>> res=new ArrayList<HashMap<String, Object>>();
        //声明用于保存结果的集合

        while(cursor.moveToNext()){//如果还有表项就循环
            String nowCourseId,nowStudentId;
            String nowGrade;
            Course course;
            Student student;
            HashMap<String, Object> object=new HashMap<String, Object>();
            //声明用于保存临时对象
            nowCourseId=cursor.getString(cursor.getColumnIndex("courseId"));
            nowStudentId=cursor.getString(cursor.getColumnIndex("studentId"));
            nowGrade=cursor.getString(cursor.getColumnIndex("grade"));
            //将查询到的列名的值与对象的属性值绑定
            course = DBAdapter.SearchCourseByCourseId(nowCourseId);
            student = DBAdapter.SearchStudentByStudentId(nowStudentId);
            object.put("courseId",nowCourseId);
            object.put("courseName",course.GetCourseName());
            object.put("studentId",nowStudentId);
            object.put("studentName",student.GetStudentName());
            object.put("grade",nowGrade);
            //将查询到的列名的值与对象的属性值绑定
            res.add(object);
            //添加到集合中
        }
        db.close();
        return res;//返回集合
    }

    public static ArrayList<HashMap<String, Object>> GetStudentByCourseId(String id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("ChooseCourseTable", new String[]{"studentId"},
                "courseId=?",  new String[]{id}, null, null, null);
        ArrayList<HashMap<String, Object>> res=new ArrayList<HashMap<String, Object>>();
        //声明用于保存结果的集合

        while(cursor.moveToNext()){//如果还有表项就循环
            String nowStudentId;
            Student student;
            HashMap<String, Object> object=new HashMap<String, Object>();
            //声明用于保存临时对象
            nowStudentId=cursor.getString(cursor.getColumnIndex("studentId"));
            //将查询到的列名的值与对象的属性值绑定
            student = DBAdapter.SearchStudentByStudentId(nowStudentId);
            object.put("studentId",student.GetStudentId());
            object.put("studentName",student.GetStudentName());
            object.put("collegeName",student.GetCollegeName());
            object.put("groupId",student.GetGroupId());
            object.put("major",student.GetMajor());
            object.put("credit",student.GetCredit());
            object.put("birthday",student.GetBirthday());
            //将查询到的列名的值与对象的属性值绑定
            res.add(object);
            //添加到集合中
        }
        db.close();
        return res;//返回集合
    }


    //删除借阅信息
    public static int DropChooseCourseByStudentIdAndCourseId(String studentId,String courseId)
    {
        SQLiteDatabase db=helper.getWritableDatabase();

        int res=db.delete("ChooseCourseTable","studentId=? and courseId=?",new String[]{studentId,courseId});
        //
        //删除 ChooseCourseTable 表中 studentId等于 studentId 并且 courseId 等于courseId 的行
        //使用 and 连接
        //
        Course course = DBAdapter.SearchCourseByCourseId(courseId);
        course.SetStuNum(course.GetStuNum()-1);//选课人数减一
        DBAdapter.UpdateCourse(courseId,course);//更新课程信息
        db.close();
        return res;
    }

    //更新选课信息
    public static int UpdateChooseCourse(String courseId ,String studentId, Integer grade)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("courseId",courseId);
        values.put("studentId",studentId);
        values.put("grade",grade);

        //以上代码将列名与属性绑定，原理同上
        int res = db.update("ChooseCourseTable", values, "courseId = ? and studentId = ?", new String[]{courseId,studentId});
        db.close();
        return res;
    }
}


