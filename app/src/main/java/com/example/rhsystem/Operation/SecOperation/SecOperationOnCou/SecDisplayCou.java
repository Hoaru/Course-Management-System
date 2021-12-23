package com.example.rhsystem.Operation.SecOperation.SecOperationOnCou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SecDisplayCou extends AppCompatActivity {
    ArrayList<HashMap<String, Object>> data = DBAdapter.GetAllCourses();//获取到集合数据
    // private Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_display_cou);
        ListView Informat = findViewById(R.id.Informat);

        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.activity_sec_display_cou_listtitle,
                new String[]{"courseId", "courseName", "collegeName", "teacherId", "teacherName", "status", "credit", "capacity", "stuNum"},
                new int[]{R.id.lt_CourseId, R.id.lt_CourseName, R.id.lt_CollegeName, R.id.lt_TeacherId, R.id.lt_TeacherName, R.id.lt_Status, R.id.lt_Credit, R.id.lt_Capacity, R.id.lt_StuNum});
        //实现列表的显示
        Informat.setAdapter(adapter);

        Informat.setOnItemClickListener(new mItemClick());
    }

    //定义列表选项监听器
    class mItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            /*arg0是指当前的listview；
             *arg1是当前listview中的item的arg1的布局,就是可用这个arg1获取里面控件id后操作控件
             * arg2是当前item在listview中适配器的位置
             * arg3是当前item在listview里第几行的位置
             */
            TextView c = (TextView) arg1.findViewById(R.id.lt_CourseId);
            String playerChanged = c.getText().toString();
            Toast.makeText(getApplicationContext(),"选择的课程号是：" + playerChanged,Toast.LENGTH_SHORT).show();
        }
    }
}
