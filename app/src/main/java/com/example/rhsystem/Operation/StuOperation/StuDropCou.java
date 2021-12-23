package com.example.rhsystem.Operation.StuOperation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rhsystem.Class.SecClass.Course;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StuDropCou extends AppCompatActivity {
    private Button bt_drop;
    private EditText edit_courseId;
    private String account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_drop_cou);
        bt_drop = findViewById(R.id.drop);
        edit_courseId = findViewById(R.id.courseId);
        account=(String)getIntent().getExtras().get("account");
        bt_drop.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {DropCourse();}

        });
    }

    public void ClearEditText(){edit_courseId.setText(""); }

    public void DropCourse() {
        String courseId = edit_courseId.getText().toString();
        Course course = DBAdapter.SearchCourseByCourseId(edit_courseId.getText().toString());
        if (course == null) {
            Toast.makeText(getApplicationContext(), "未找到该课程", Toast.LENGTH_SHORT).show();
            ClearEditText();
        }
        else{
            int res = DBAdapter.DropChooseCourseByStudentIdAndCourseId(account,courseId);
            if (res == 1)
                Toast.makeText(getApplicationContext(), "退课成功", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "退课失败", Toast.LENGTH_SHORT).show();
            ClearEditText();
        }
    }
}
