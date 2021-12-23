package com.example.rhsystem.Operation.SecOperation;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rhsystem.DataBase.DBAdapter;
import com.example.rhsystem.R;
import com.example.rhsystem.Operation.SecOperation.SecOperationOnCou.SecCouSurface;
import com.example.rhsystem.Operation.SecOperation.SecOperationOnStu.SecStuSurface;

public class SecSurface extends AppCompatActivity {
    private static final int item1 = Menu.FIRST;
    private static final int item2 = Menu.FIRST + 1;
    //    private static final int item3 = Menu.FIRST + 2;
    private boolean mIsExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_surface);
        DBAdapter.Init(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0, item1, 0, "学生管理");
        menu.add(0, item2, 0, "课程管理");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(SecSurface.this);
        switch (item.getItemId()) {
            case item1:
                Intent intent1 = new Intent();
                intent1.setClass(SecSurface.this, SecStuSurface.class);
                SecSurface.this.startActivity(intent1);
                break;
            case item2:
                Intent intent2 = new Intent();
                intent2.setClass(SecSurface.this, SecCouSurface.class);
                SecSurface.this.startActivity(intent2);
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
