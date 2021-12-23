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

public class SecModifyCou extends AppCompatActivity {

    private Button bt_search;
    private EditText edit_courseId_first;

    public void ClearEditText_first()
    {
        edit_courseId_first.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_modify_cou);
        bt_search = findViewById(R.id.search);
        edit_courseId_first = findViewById(R.id.courseId_first);

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edit_courseId_first.getText().toString();
                Course cou = DBAdapter.SearchCourseByCourseId(id);
                if (cou == null){
                    Toast.makeText(getApplicationContext(),"未找到该课程！",Toast.LENGTH_LONG).show();
                    ClearEditText_first();
                }
                else {
                    ModifyCourseSurface(cou);
                }
            }
        });

    }

    public void ModifyCourseSurface (Course cou)
    {
        Button bt_submit;
        final EditText edit_courseId, edit_courseName, edit_collegeName, edit_teacherId, edit_teacherName, edit_status, edit_credit, edit_capacity, edit_stuNum;
        //很多的输入框
        SecModifyCou.this.setContentView(R.layout.activity_sec_modify_cou_content);

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

        edit_courseId.setText(cou.GetCourseId());
        edit_courseName.setText(cou.GetCourseName());
        edit_collegeName.setText(cou.GetCollegeName());
        edit_teacherId.setText(cou.GetTeacherId());
        edit_teacherName.setText(cou.GetTeacherName());
        edit_status.setText(cou.GetStatus());
        edit_credit.setText(Integer.toString(cou.GetCredit()));
        edit_capacity.setText(Integer.toString(cou.GetCapacity()));
        edit_stuNum.setText(Integer.toString(cou.GetStuNum()));

//        public void ClearEditText(){
//            edit_courseId.setText("");
//            edit_courseName.setText("");
//            edit_collegeName.setText("");
//            edit_teacherId.setText("");
//            edit_teacherName.setText("");
//            edit_status.setText("");
//            edit_credit.setText("");
//            edit_capacity.setText("");
//            edit_stuNum.setText("");
//        }
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_courseId.getText().toString().equals("") || edit_courseName.getText().toString().equals("") ||
                        edit_collegeName.getText().toString().equals("") || edit_teacherId.getText().toString().equals("") ||
                        edit_teacherName.getText().toString().equals("") || edit_status.getText().toString().equals("") ||
                        edit_credit.getText().toString().equals("") || edit_capacity.getText().toString().equals("") ||
                        edit_stuNum.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "请填写完整", Toast.LENGTH_SHORT).show();
                }
                Course newcourse = new Course(
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
                long r = DBAdapter.UpdateCourse(edit_courseId.getText().toString(),newcourse);
                //添加到数据库中
                if(r == 1)
                    Toast.makeText(getApplicationContext(), "修改课程成功", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "修改课程失败", Toast.LENGTH_SHORT).show();
                return;
            }
        });
        //提交按钮绑定监视器
    }

}