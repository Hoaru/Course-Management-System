package com.example.rhsystem.Operation.SecOperation.SecOperationOnStu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhsystem.Class.SecClass.Student;
import com.example.rhsystem.Class.User;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

public class SecAddStu extends AppCompatActivity {

    private Button bt_submit,bt_clear;
    //提交按钮和重新填写按钮
    private EditText edit_studentId,edit_studentName,edit_collegeName,edit_groupId,edit_major,edit_credit,edit_birthday;
    //很多的输入框
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_add_stu);
        edit_studentId = findViewById(R.id.studentId);
        edit_studentName = findViewById(R.id.studentName);
        edit_collegeName = findViewById(R.id.collegeName);
        edit_groupId = findViewById(R.id.groupId);
        edit_major = findViewById(R.id.major);
        edit_credit = findViewById(R.id.credit);
        edit_birthday = findViewById(R.id.birthday);
        bt_submit = findViewById(R.id.submit);
        bt_clear = findViewById(R.id.clear);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { AddStudent(); }
        });
        //提交按钮绑定监视器

        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearEditText();
            }
        });
        //清空按钮绑定监视器
    }

    public void ClearEditText()
    {
        edit_studentId.setText("");
        edit_studentName.setText("");
        edit_collegeName.setText("");
        edit_groupId.setText("");
        edit_major.setText("");
        edit_credit.setText("");
        edit_birthday.setText("");
    }
    /*
     *添加学生方法，首先检验用户的输入是否合法，若合法，则产生图书对象，添加到数据库里。
     * 若不合法，给用户提示
     */
    public void  AddStudent()
    {

        if (edit_studentId.getText().toString().equals("") || edit_studentName.getText().toString().equals("") ||
                edit_collegeName.getText().toString().equals("") || edit_groupId.getText().toString().equals("") ||
                edit_major.getText().toString().equals("") || edit_credit.getText().toString().equals("") ||
                edit_birthday.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "请填写完整", Toast.LENGTH_SHORT).show();
        }
        else{
            Student student = new Student(

                    edit_studentId.getText().toString(),
                    edit_studentName.getText().toString(),
                    edit_collegeName.getText().toString(),
                    edit_groupId.getText().toString(),
                    edit_major.getText().toString(),
                    edit_credit.getText().toString(),
                    edit_birthday.getText().toString()
            );
            User user = new User(
                    edit_studentId.getText().toString(),
                    edit_studentId.getText().toString(),
                    1
            );
            long r1= DBAdapter.InsertStudent(student);
            long r2 = DBAdapter.InsertUser(user);
            //添加到数据库中
            if(r1==-1 || r2 ==-1)//添加失败
            {
                Toast.makeText(this, "添加学生失败", Toast.LENGTH_SHORT).show();
                return ;
            }
            else//成功添加
            {
                Toast.makeText(this, "添加学生成功", Toast.LENGTH_SHORT).show();
                return ;
            }

        }
    }
}
