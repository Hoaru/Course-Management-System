package com.example.rhsystem.Operation.SecOperation.SecOperationOnStu;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhsystem.Class.SecClass.Student;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

public class SecSearchStu extends AppCompatActivity {

    private Button bt_search;
    //提交按钮和重新填写按钮
    private EditText edit_studentId;
    //很多的输入框
    private TextView text_display;

    public void ClearEditText()
    {
        edit_studentId.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_search_stu);
        bt_search = findViewById(R.id.search);
        edit_studentId = findViewById(R.id.studentId);
        text_display = findViewById(R.id.display);
        bt_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String id = edit_studentId.getText().toString();
                Student stu = DBAdapter.SearchStudentByStudentId(id);
                if (stu == null){
                    text_display.setText("未找到该学生！");
                    ClearEditText();}
                else {
                    String info = String.format(
                            "学号: %s\n姓名: %s\n学院: %s\n班级: %s\n" +
                                    "专业: %s\n绩点: %s\n生日: %s",
                            stu.GetStudentId(),
                            stu.GetStudentName(),
                            stu.GetCollegeName(),
                            stu.GetGroupId(),
                            stu.GetMajor(),
                            stu.GetCredit(),
                            stu.GetBirthday());
                    text_display.setText(info);}
            }
        });
    }
}
