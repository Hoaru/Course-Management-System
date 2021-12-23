package com.example.rhsystem.Operation.TeaOperation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.rhsystem.Class.SecClass.Course;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TeaDisplayStuGrades extends AppCompatActivity {

    private Button bt_search;
    private EditText edit_courseId_first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_display_stu_grades);
        bt_search = findViewById(R.id.search);
        edit_courseId_first = findViewById(R.id.courseId_first);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { SearchCourse(); }
        });
    }

    public void ClearEditText()
    {
        edit_courseId_first.setText("");
    }

    public void SearchCourse()
    {

        if (edit_courseId_first.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "请填写完整", Toast.LENGTH_SHORT).show();
        }
        else{
            Course course = DBAdapter.SearchCourseByCourseId(edit_courseId_first.getText().toString());
            if (course ==null) {
                Toast.makeText(getApplicationContext(), "未找到该课程", Toast.LENGTH_SHORT).show();
                ClearEditText();
            }
            else {
                DisplayGrades(edit_courseId_first.getText().toString());
            }
        }
    }

    public void DisplayGrades(String courseId)
    {
        setContentView(R.layout.activity_tea_display_stu_grades_content);
        ListView Informat = findViewById(R.id.Informat);
        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        ArrayList<HashMap<String, Object>> data = DBAdapter.GetChooseCourseByCourseId(courseId);//获取到集合数据
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.activity_tea_display_stu_grades_content_listtitle,
                new String[]{"courseId", "courseName", "studentId", "studentName", "grade"},
                new int[]{R.id.lt_CourseId, R.id.lt_CourseName, R.id.lt_StudentId, R.id.lt_StudentName, R.id.lt_Grade});
        //实现列表的显示
        Informat.setAdapter(adapter);
    }
}

