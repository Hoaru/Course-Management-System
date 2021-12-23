package com.example.rhsystem.Operation.TeaOperation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.example.rhsystem.Class.SecClass.Course;
import com.example.rhsystem.Class.StuClass.ChooseCourse;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TeaGiveGrades extends AppCompatActivity {

    private Button bt_search;
    private EditText edit_courseId_first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_give_grades);
        bt_search = findViewById(R.id.search);
        edit_courseId_first = findViewById(R.id.courseId_first);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { SearchCourse(); }
        });
    }

    public void ClearEditText()
    {
        edit_courseId_first.setText("");
    }

    public void SearchCourse()
    {

        if (edit_courseId_first.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "请填写完整", Toast.LENGTH_SHORT).show();
        }
        else{
            Course course = DBAdapter.SearchCourseByCourseId(edit_courseId_first.getText().toString());
            if (course ==null) {
                Toast.makeText(getApplicationContext(), "未找到该课程", Toast.LENGTH_SHORT).show();
                ClearEditText();
            }
            else {
                GiveGrades(edit_courseId_first.getText().toString());
            }
        }
    }

    public void GiveGrades(final String courseId)
    {
        final String Id = courseId;
        final Button bt_submit;
        final TextView tv_title,tv_stu1,tv_stu2,tv_stu3,tv_stu4,tv_stu5;
        final EditText edit_grade1,edit_grade2,edit_grade3,edit_grade4,edit_grade5;
        setContentView(R.layout.activity_tea_give_grades_content);

        bt_submit = findViewById(R.id.submit);
        tv_title = findViewById(R.id.textView0);
        tv_stu1 = findViewById(R.id.textView1);
        tv_stu2 = findViewById(R.id.textView2);
        tv_stu3 = findViewById(R.id.textView3);
        tv_stu4 = findViewById(R.id.textView4);
        tv_stu5 = findViewById(R.id.textView5);
        edit_grade1 = findViewById(R.id.grade1);
        edit_grade2 = findViewById(R.id.grade2);
        edit_grade3 = findViewById(R.id.grade3);
        edit_grade4 = findViewById(R.id.grade4);
        edit_grade5 = findViewById(R.id.grade5);

        final ArrayList<HashMap<String, Object>> data = DBAdapter.GetChooseCourseByCourseId(courseId);//get成绩、姓名
        Course course = DBAdapter.SearchCourseByCourseId(courseId);//get课程名称
        tv_title.setText("录入成绩——"+courseId+" "+course.GetCourseName());

        final int size = data.size();
        if(size == 0){
            tv_stu1.setText("无选课学生");
            tv_stu2.setVisibility(View.GONE);
            tv_stu3.setVisibility(View.GONE);
            tv_stu4.setVisibility(View.GONE);
            tv_stu5.setVisibility(View.GONE);
            edit_grade1.setVisibility(View.GONE);
            edit_grade2.setVisibility(View.GONE);
            edit_grade3.setVisibility(View.GONE);
            edit_grade4.setVisibility(View.GONE);
            edit_grade5.setVisibility(View.GONE);
        }
        else if(size == 1){
            tv_stu1.setText(String.valueOf(data.get(0).get("studentName")));
            tv_stu2.setVisibility(View.GONE);
            tv_stu3.setVisibility(View.GONE);
            tv_stu4.setVisibility(View.GONE);
            tv_stu5.setVisibility(View.GONE);
            edit_grade1.setText(String.valueOf(data.get(0).get("grade")));
            edit_grade2.setVisibility(View.GONE);
            edit_grade3.setVisibility(View.GONE);
            edit_grade4.setVisibility(View.GONE);
            edit_grade5.setVisibility(View.GONE);
        }
        else if(size == 2){
            tv_stu1.setText(String.valueOf(data.get(0).get("studentName")));

            tv_stu2.setText(String.valueOf(data.get(1).get("studentName")));
            tv_stu3.setVisibility(View.GONE);
            tv_stu4.setVisibility(View.GONE);
            tv_stu5.setVisibility(View.GONE);
            edit_grade1.setText(String.valueOf(data.get(0).get("grade")));
            edit_grade2.setText(String.valueOf(data.get(1).get("grade")));
            edit_grade3.setVisibility(View.GONE);
            edit_grade4.setVisibility(View.GONE);
            edit_grade5.setVisibility(View.GONE);
        }
        else if(size == 3){
            tv_stu1.setText(String.valueOf(data.get(0).get("studentName")));
            tv_stu2.setText(String.valueOf(data.get(1).get("studentName")));
            tv_stu3.setText(String.valueOf(data.get(2).get("studentName")));
            tv_stu4.setVisibility(View.GONE);
            tv_stu5.setVisibility(View.GONE);
            edit_grade1.setText(String.valueOf(data.get(0).get("grade")));
            edit_grade2.setText(String.valueOf(data.get(1).get("grade")));
            edit_grade3.setText(String.valueOf(data.get(2).get("grade")));
            edit_grade4.setVisibility(View.GONE);
            edit_grade5.setVisibility(View.GONE);
        }
        else if(size == 4){
            tv_stu1.setText(String.valueOf(data.get(0).get("studentName")));
            tv_stu2.setText(String.valueOf(data.get(1).get("studentName")));
            tv_stu3.setText(String.valueOf(data.get(2).get("studentName")));
            tv_stu4.setText(String.valueOf(data.get(3).get("studentName")));
            tv_stu5.setVisibility(View.GONE);
            edit_grade1.setText(String.valueOf(data.get(0).get("grade")));
            edit_grade2.setText(String.valueOf(data.get(1).get("grade")));
            edit_grade3.setText(String.valueOf(data.get(2).get("grade")));
            edit_grade4.setText(String.valueOf(data.get(3).get("grade")));
            edit_grade5.setVisibility(View.GONE);
        }
        else if(size == 5){
            tv_stu1.setText(String.valueOf(data.get(0).get("studentName")));
            tv_stu2.setText(String.valueOf(data.get(1).get("studentName")));
            tv_stu3.setText(String.valueOf(data.get(2).get("studentName")));
            tv_stu4.setText(String.valueOf(data.get(3).get("studentName")));
            tv_stu5.setText(String.valueOf(data.get(4).get("studentName")));
            edit_grade1.setText(String.valueOf(data.get(0).get("grade")));
            edit_grade2.setText(String.valueOf(data.get(1).get("grade")));
            edit_grade3.setText(String.valueOf(data.get(2).get("grade")));
            edit_grade4.setText(String.valueOf(data.get(3).get("grade")));
            edit_grade5.setText(String.valueOf(data.get(4).get("grade")));
        }


        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long r1,r2,r3,r4,r5;
                if(size == 0)
                    Toast.makeText(getApplicationContext(), "无选课学生！", Toast.LENGTH_SHORT).show();
                else if(size == 1) {
                    r1 = DBAdapter.UpdateChooseCourse(courseId, String.valueOf(data.get(0).get("studentId")),
                            Integer.parseInt(edit_grade1.getText().toString()));
                    if(r1 == 1)
                        Toast.makeText(getApplicationContext(), "录入成绩成功", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "录入成绩失败", Toast.LENGTH_SHORT).show();

                }
                else if(size == 2) {
                    String studentId = String.valueOf(data.get(0).get("studentId"));
                    Integer grade = Integer.parseInt(edit_grade1.getText().toString());
                    r1 = DBAdapter.UpdateChooseCourse(courseId, studentId, grade);
//                    r1 = DBAdapter.UpdateChooseCourse(courseId, String.valueOf(students.get(0).get("studentId")),
//                            Integer.parseInt(edit_grade1.getText().toString()));
                    r2 = DBAdapter.UpdateChooseCourse(courseId, String.valueOf(data.get(1).get("studentId")),
                            Integer.parseInt(edit_grade2.getText().toString()));
                    if(r1 == 1&&r2 == 1)
                        Toast.makeText(getApplicationContext(), "录入成绩成功", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "录入成绩失败", Toast.LENGTH_SHORT).show();

                }
                else if(size == 3) {
                    r1 = DBAdapter.UpdateChooseCourse(courseId, String.valueOf(data.get(0).get("studentId")),
                            Integer.parseInt(edit_grade1.getText().toString()));
                    r2 = DBAdapter.UpdateChooseCourse(courseId, String.valueOf(data.get(1).get("studentId")),
                            Integer.parseInt(edit_grade2.getText().toString()));
                    r3 = DBAdapter.UpdateChooseCourse(courseId,String.valueOf(data.get(2).get("studentId")),
                            Integer.parseInt(edit_grade3.getText().toString()));
                    if(r1 == 1&&r2 == 1&&r3 == 1)
                        Toast.makeText(getApplicationContext(), "录入成绩成功", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "录入成绩失败", Toast.LENGTH_SHORT).show();

                }
                else if(size == 4) {
                    r1 = DBAdapter.UpdateChooseCourse(courseId, String.valueOf(data.get(0).get("studentId")),
                            Integer.parseInt(edit_grade1.getText().toString()));
                    r2 = DBAdapter.UpdateChooseCourse(courseId, String.valueOf(data.get(1).get("studentId")),
                            Integer.parseInt(edit_grade2.getText().toString()));
                    r3 = DBAdapter.UpdateChooseCourse(courseId,String.valueOf(data.get(2).get("studentId")),
                            Integer.parseInt(edit_grade3.getText().toString()));
                    r4 = DBAdapter.UpdateChooseCourse(courseId,String.valueOf(data.get(3).get("studentId")),
                            Integer.parseInt(edit_grade4.getText().toString()));
                    if(r1 == 1&&r2 == 1&&r3 ==1 &&r4 == 1)
                        Toast.makeText(getApplicationContext(), "录入成绩成功", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "录入成绩失败", Toast.LENGTH_SHORT).show();

                }
                else if(size == 5) {
                    r1 = DBAdapter.UpdateChooseCourse(courseId, String.valueOf(data.get(0).get("studentId")),
                            Integer.parseInt(edit_grade1.getText().toString()));
                    r2 = DBAdapter.UpdateChooseCourse(courseId, String.valueOf(data.get(1).get("studentId")),
                            Integer.parseInt(edit_grade2.getText().toString()));
                    r3 = DBAdapter.UpdateChooseCourse(courseId,String.valueOf(data.get(2).get("studentId")),
                            Integer.parseInt(edit_grade3.getText().toString()));
                    r4 = DBAdapter.UpdateChooseCourse(courseId,String.valueOf(data.get(3).get("studentId")),
                            Integer.parseInt(edit_grade4.getText().toString()));
                    r5 = DBAdapter.UpdateChooseCourse(courseId,String.valueOf(data.get(4).get("studentId")),
                            Integer.parseInt(edit_grade5.getText().toString()));
                    if(r1 == 1&&r2 == 1&&r3 ==1 &&r4 == 1&&r5 ==1)
                        Toast.makeText(getApplicationContext(), "录入成绩成功", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "录入成绩失败", Toast.LENGTH_SHORT).show();
                }

                //return;
            }
        });

    }

}

