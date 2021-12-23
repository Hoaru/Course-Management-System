package com.example.rhsystem.Operation.SecOperation.SecOperationOnCou;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhsystem.Class.SecClass.Course;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

public class SecAddCou extends AppCompatActivity {

    private Button bt_submit,bt_clear;
    //提交按钮和重新填写按钮
    private EditText edit_courseId,edit_courseName,edit_collegeName,edit_teacherId,
            edit_teacherName,edit_status,edit_credit,edit_capacity,edit_stuNum;
    //很多的输入框
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_add_cou);

        edit_courseId = findViewById(R.id.courseId);
        edit_courseName = findViewById(R.id.courseName);
        edit_collegeName = findViewById(R.id.collegeName);
        edit_teacherId = findViewById(R.id.teacherId);
        edit_teacherName = findViewById(R.id.teacherName);
        edit_status = findViewById(R.id.status);
        edit_credit = findViewById(R.id.credit);
        edit_capacity = findViewById(R.id.capacity);
        edit_stuNum = findViewById(R.id.stuNum);
        bt_submit = findViewById(R.id.submit);
        bt_clear = findViewById(R.id.clear);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { AddCourse(); }
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
        edit_courseId.setText("");
        edit_courseName.setText("");
        edit_collegeName.setText("");
        edit_teacherId.setText("");
        edit_teacherName.setText("");
        edit_status.setText("");
        edit_credit.setText("");
        edit_capacity.setText("");
        edit_stuNum.setText("");
    }
    /*
     *添加课程方法，首先检验用户的输入是否合法，若合法，则产生图书对象，添加到数据库里。
     * 若不合法，给用户提示
     */
    public void  AddCourse()
    {

        if (edit_courseId.getText().toString().equals("") || edit_courseName.getText().toString().equals("") ||
                edit_collegeName.getText().toString().equals("") || edit_teacherId.getText().toString().equals("") ||
                edit_teacherName.getText().toString().equals("") || edit_status.getText().toString().equals("") ||
                edit_credit.getText().toString().equals("")|| edit_capacity.getText().toString().equals("") ||
                edit_stuNum.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "请填写完整", Toast.LENGTH_SHORT).show();
        }
        else{
            Course course=new Course(
                    edit_courseId.getText().toString(),
                    edit_courseName.getText().toString(),
                    edit_collegeName.getText().toString(),
                    edit_teacherId.getText().toString(),
                    edit_teacherName.getText().toString(),
                    edit_status.getText().toString(),
                    Integer.parseInt(edit_credit.getText().toString()),
                    Integer.parseInt(edit_capacity.getText().toString()),
                    Integer.parseInt(edit_stuNum.getText().toString())
            );
            long r= DBAdapter.InsertCourse(course);
            //添加到数据库中
            if(r==-1)//添加失败
            {
                Toast.makeText(getApplicationContext(), "添加课程失败", Toast.LENGTH_SHORT).show();
                return ;
            }
            else//成功添加
            {
                Toast.makeText(getApplicationContext(), "添加课程成功", Toast.LENGTH_SHORT).show();
                return ;
            }

        }
    }
}
