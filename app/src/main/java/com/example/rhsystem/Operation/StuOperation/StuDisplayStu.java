package com.example.rhsystem.Operation.StuOperation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhsystem.Class.SecClass.Student;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

import java.util.ArrayList;
import java.util.HashMap;

public class StuDisplayStu extends AppCompatActivity {
    // private Context con;
    private String account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        account=(String)getIntent().getExtras().get("account");
        ArrayList<HashMap<String, Object>> student = DBAdapter.GetAStudent(account);//获取到集合数据
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_display_stu);
        ListView Informat = findViewById(R.id.Informat);

        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        SimpleAdapter adapter = new SimpleAdapter(this, student, R.layout.activity_stu_display_stu_listtitle,
                new String[]{"studentId", "studentName", "collegeName", "groupId", "major", "credit", "birthday"},
                new int[]{R.id.lt_StudentId, R.id.lt_StudentName, R.id.lt_CollegeName, R.id.lt_GroupId, R.id.lt_Major, R.id.lt_Credit, R.id.lt_Birthday});
        //实现列表的显示
        Informat.setAdapter(adapter);

        Informat.setOnItemClickListener(new com.example.rhsystem.Operation.StuOperation.StuDisplayStu.mItemClick());
    }

    //定义列表选项监听器
    class mItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            TextView c = (TextView) arg1.findViewById(R.id.lt_CourseId);
            String playerChanged = c.getText().toString();
            Toast.makeText(getApplicationContext(),"选择的课程号是：" + playerChanged,Toast.LENGTH_SHORT).show();
        }
    }
}