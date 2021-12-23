package com.example.rhsystem.Operation.StuOperation;



import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;

public class StuSurface extends AppCompatActivity {
    private static final int item1 = Menu.FIRST;
    private static final int item2 = Menu.FIRST + 1;
    private static final int item3 = Menu.FIRST + 2;
    private static final int item4 = Menu.FIRST + 3;
    private boolean mIsExit;
    private String account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_surface);
        DBAdapter.Init(this);
        account=(String)getIntent().getStringExtra("account");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0, item1, 0, "选课");
        menu.add(0, item2, 0, "退课");
        menu.add(0, item3, 0, "显示课程");
        menu.add(0, item4, 0, "显示个人信息");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(StuSurface.this);
        switch (item.getItemId()) {
            case item1:
                Intent intent1 = new Intent();
                intent1.setClass(StuSurface.this, StuChooseCou.class);
                intent1.putExtra("account",account);
                StuSurface.this.startActivity(intent1);
                break;
            case item2:
                Intent intent2 = new Intent();
                intent2.setClass(StuSurface.this, StuDropCou.class);
                intent2.putExtra("account",account);
                StuSurface.this.startActivity(intent2);
                break;
            case item3:
                Intent intent3 = new Intent();
                intent3.setClass(StuSurface.this, StuDisplayCou.class);
                intent3.putExtra("account",account);
                StuSurface.this.startActivity(intent3);
                break;
            case item4:
                Intent intent4 = new Intent();
                intent4.setClass(StuSurface.this, StuDisplayStu.class);
                intent4.putExtra("account",account);
                StuSurface.this.startActivity(intent4);
                break;
        }
        return true;
    }

    /*  普通对话框的“确定”按钮事件 */
    class okClick implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            dialog.cancel();
        }
    }

}
