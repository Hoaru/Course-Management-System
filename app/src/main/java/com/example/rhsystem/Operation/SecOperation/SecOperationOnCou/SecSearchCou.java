package com.example.rhsystem.Operation.SecOperation.SecOperationOnCou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhsystem.Class.SecClass.Course;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

public class SecSearchCou extends AppCompatActivity {

    private Button bt_search;
    private EditText edit_courseId;
    private TextView text_display;
    public void ClearEditText()
    {
        edit_courseId.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_search_cou);
        bt_search = findViewById(R.id.search);
        edit_courseId = findViewById(R.id.courseId);
        text_display = findViewById(R.id.display);
        bt_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String id = edit_courseId.getText().toString();
                Course cou = DBAdapter.SearchCourseByCourseId(id);
                if (cou == null){
                    text_display.setText("未找到该课程！");
                    ClearEditText();}
                else {
                    String info = String.format(
                            "课程号: %s\n课程名称: %s\n学院: %s\n教师工号: %s\n" +
                                    "任课教师: %s\n类型: %s\n学分: %s\n容量: %s\n已选人数: %s",
                            cou.GetCourseId(),
                            cou.GetCourseName(),
                            cou.GetCollegeName(),
                            cou.GetTeacherId(),
                            cou.GetTeacherName(),
                            cou.GetStatus(),
                            cou.GetCredit(),
                            cou.GetCapacity(),
                            cou.GetStuNum());
                    text_display.setText(info);}
            }
        });
    }
}
