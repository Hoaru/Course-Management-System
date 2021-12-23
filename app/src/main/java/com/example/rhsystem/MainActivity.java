package com.example.rhsystem;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhsystem.Class.User;
import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.Operation.SecOperation.SecSurface;
import com.example.rhsystem.Operation.StuOperation.StuSurface;
import com.example.rhsystem.Operation.TeaOperation.TeaSurface;

//import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends AppCompatActivity {
    private EditText edit_account;
    private EditText edit_password;
    private Button bt_log;
    private Button bt_clear;
    private boolean mIsExit=false;
    //用户退出的变量，同 low / middle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //开启Service
//        SQLiteStudioService.instance().start(this);
        //关闭Service
        //SQLiteStudioService.instance().stop(this);
        DBAdapter.Init(this);

        edit_account=findViewById(R.id.account);
        edit_password=findViewById(R.id.password);
        bt_log=findViewById(R.id.log);
        bt_clear=findViewById(R.id.clear);

        edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearEditText();
            }
        });
        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log();
                //AddUser();
            }
        });

    }


    public void ClearEditText()//清除输入框的方法
    {
        edit_account.setText("");
        edit_password.setText("");
    }


    //处理用户点击登录按钮
    public void Log()
    {
        String account = edit_account.getText().toString();//拿到用户输入的id
        String password = edit_password.getText().toString();//拿到密码
        if(account.equals("")||password.equals(""))//如果有一项没有输入
        {
            Toast.makeText(getApplicationContext(),"请输入用户名和密码",Toast.LENGTH_SHORT).show();
            ClearEditText();//清除输入框
            return ;
        }
        User user= DBAdapter.SearchUserByAccount(account);//全部输入根据id查询
        if(user==null||!user.GetPassword().equals(password))//若没有找到活密码不相等
        {
            Toast.makeText(getApplicationContext(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
            ClearEditText();
            return ;
        }

        ClearEditText();
        if(user.GetIdentity()==0)//为管理员 跳转到SecSurface
        {
            Intent intent=new Intent(MainActivity.this, SecSurface.class);
            intent.putExtra("account",user.GetAccount());
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        else if(user.GetIdentity()==1)//为学生 跳转到 StuSurface
        {
            Intent intent=new Intent(MainActivity.this, StuSurface.class);
            intent.putExtra("account",user.GetAccount());
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        else if(user.GetIdentity()==2)//为老师 跳转到 TeaSurface
        {
            Intent intent=new Intent(MainActivity.this, TeaSurface.class);
            intent.putExtra("account",user.GetAccount());
            startActivity(intent);
            overridePendingTransition(0,0);
        }

    }
    public void  AddUser()
    {
        Integer identity = Integer.valueOf((edit_account.getText().toString()).charAt(0));

        if (edit_account.getText().toString().equals("") || edit_password.getText().toString().equals("") )
        {
            Toast.makeText(getApplicationContext(), "请填写完整", Toast.LENGTH_SHORT).show();
        }
        else{
            User user=new User(
                    edit_account.getText().toString(),
                    edit_password.getText().toString(),
                    0//0，1，2
            );
            long r= DBAdapter.InsertUser(user);
            //添加到数据库中
            if(r==-1)//添加账号失败
            {
                Toast.makeText(this, "添加账号失败", Toast.LENGTH_SHORT).show();
                //ClearEditText();
                return ;
            }
            else//成功添加
            {
                Toast.makeText(this, "添加账号成功", Toast.LENGTH_SHORT).show();
                //ClearEditText();
                return ;
            }

        }
    }
}