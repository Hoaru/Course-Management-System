package com.example.rhsystem.Operation.SecOperation.SecOperationOnStu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhsystem.Class.SecClass.Student;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

public class SecModifyStu extends AppCompatActivity {

    private Button bt_search;
    private EditText edit_studentId_first;

    public void ClearEditText()
    {
        edit_studentId_first.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_modify_stu);
        bt_search = findViewById(R.id.search);
        edit_studentId_first = findViewById(R.id.studentId_first);

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edit_studentId_first.getText().toString();
                Student stu = DBAdapter.SearchStudentByStudentId(id);
                if (stu == null){
                    Toast.makeText(getApplicationContext(),"未找到该学生！",Toast.LENGTH_LONG).show();
                    ClearEditText();
                }
                else {
                    ModifyStudentSurface(stu);
                }
            }
        });

    }

    public void ModifyStudentSurface (Student stu)
    {
        Button bt_submit;
        final EditText edit_studentId, edit_studentName, edit_collegeName, edit_groupId, edit_major, edit_credit, edit_birthday;
        //很多的输入框
        SecModifyStu.this.setContentView(R.layout.activity_sec_modify_stu_content);
        edit_studentId = findViewById(R.id.studentId);
        edit_studentName = findViewById(R.id.studentName);
        edit_collegeName = findViewById(R.id.collegeName);
        edit_groupId = findViewById(R.id.groupId);
        edit_major = findViewById(R.id.major);
        edit_credit = findViewById(R.id.credit);
        edit_birthday = findViewById(R.id.birthday);
        bt_submit = findViewById(R.id.submit);

        edit_studentId.setText(stu.GetStudentId());
        edit_studentName.setText(stu.GetStudentName());
        edit_collegeName.setText(stu.GetCollegeName());
        edit_groupId.setText(stu.GetGroupId());
        edit_major.setText(stu.GetMajor());
        edit_credit.setText(stu.GetCredit());
        edit_birthday.setText(stu.GetBirthday());

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_studentId.getText().toString().equals("") || edit_studentName.getText().toString().equals("") ||
                        edit_collegeName.getText().toString().equals("") || edit_groupId.getText().toString().equals("") ||
                        edit_major.getText().toString().equals("") || edit_credit.getText().toString().equals("") ||
                        edit_birthday.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "请填写完整", Toast.LENGTH_SHORT).show();
                }
                Student newstudent = new Student(
                        edit_studentId.getText().toString(),
                        edit_studentName.getText().toString(),
                        edit_collegeName.getText().toString(),
                        edit_groupId.getText().toString(),
                        edit_major.getText().toString(),
                        edit_credit.getText().toString(),
                        edit_birthday.getText().toString()
                );
                long r = DBAdapter.UpdateStudent(edit_studentId.getText().toString(),newstudent);
                //添加到数据库中
                if(r == 1)
                    Toast.makeText(getApplicationContext(), "修改学生成功", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "修改学生失败", Toast.LENGTH_SHORT).show();
            }
        });
        //提交按钮绑定监视器
    }

}