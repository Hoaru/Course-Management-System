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

public class SecDeleteStu extends AppCompatActivity {

    private Button bt_delete;
    //提交按钮和重新填写按钮
    private EditText edit_studentId;
    //很多的输入框
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_delete_stu);
        bt_delete = findViewById(R.id.delete);
        edit_studentId = findViewById(R.id.studentId);
        bt_delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {DeleteStudent();}
        });
    }

    public void ClearEditText()
    {
        edit_studentId.setText("");
    }

    public void DeleteStudent() {
        String studentId = edit_studentId.getText().toString();
        Student student = DBAdapter.SearchStudentByStudentId(studentId);
        if(student == null) {
            Toast.makeText(getApplicationContext(), "未找到该学生", Toast.LENGTH_SHORT).show();
            ClearEditText();
        }
        else{

            int res = DBAdapter.DeleteStudentByStudentId(studentId);
            if (res == 1)
                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();
            ClearEditText();
        }
    }
}