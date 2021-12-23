package com.example.rhsystem.Operation.StuOperation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhsystem.Class.SecClass.Course;
import com.example.rhsystem.Class.SecClass.Student;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

public class StuChooseCou extends AppCompatActivity {

private Button bt_choose;
private EditText edit_courseId;
private String account;
@Override
protected void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stu_choose_cou);
    bt_choose = findViewById(R.id.choose);
    edit_courseId = findViewById(R.id.courseId);
    account=(String)getIntent().getExtras().get("account");
    bt_choose.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) { ChooseCourse(); }
    });
}

    public void ClearEditText()
    {
        edit_courseId.setText("");
    }

    public void  ChooseCourse()
    {

        if (edit_courseId.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "请填写完整", Toast.LENGTH_SHORT).show();
        }
        else{
            Student student = DBAdapter.SearchStudentByStudentId(account);
            Course course = DBAdapter.SearchCourseByCourseId(edit_courseId.getText().toString());
            if (course ==null) {
                Toast.makeText(getApplicationContext(), "未找到该课程", Toast.LENGTH_SHORT).show();
                ClearEditText();
            }
            else {
                long r = DBAdapter.InsertChooseCourse(student, course);
                //添加到数据库中
                if (r == -1)//添加失败
                    Toast.makeText(getApplicationContext(), "选课失败", Toast.LENGTH_SHORT).show();
                else if (r == 8)//人数已满
                    Toast.makeText(getApplicationContext(), "人数已满无法选课", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "选课成功", Toast.LENGTH_SHORT).show();
            }
            ClearEditText();
        }
    }
}

