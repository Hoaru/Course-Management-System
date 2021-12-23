package com.example.rhsystem.Operation.SecOperation.SecOperationOnCou;

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

public class SecDeleteCou extends AppCompatActivity {

    private Button bt_delete;
    //提交按钮和重新填写按钮
    private EditText edit_courseId;
    //很多的输入框
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_delete_cou);
        bt_delete = findViewById(R.id.delete);
        edit_courseId = findViewById(R.id.courseId);
        bt_delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {DeleteCourse();}
        });
    }

    public void ClearEditText()
    {
        edit_courseId.setText("");
    }

    public void DeleteCourse() {
        String courseId = edit_courseId.getText().toString();
        Course course = DBAdapter.SearchCourseByCourseId(courseId);
        if(course == null) {
            Toast.makeText(getApplicationContext(), "未找到该课程", Toast.LENGTH_SHORT).show();
            ClearEditText();
        }
        else{
            int res = DBAdapter.DeleteCourseByCourseId(courseId);
            if (res == 1)
                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();
            ClearEditText();
        }

    }
}