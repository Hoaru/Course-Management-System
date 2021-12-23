package com.example.rhsystem.Operation.SecOperation.SecOperationOnCou;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rhsystem.R;

public class SecCouSurface extends AppCompatActivity {
    private static final int item1 = Menu.FIRST;
    private static final int item2 = Menu.FIRST + 1;
    private static final int item3 = Menu.FIRST + 2;
    private static final int item4 = Menu.FIRST + 3;
    private static final int item5 = Menu.FIRST + 4;
    private boolean mIsExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_cou_surface);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, item1, 0, "增添课程");
        menu.add(0, item2, 0, "删除课程");
        menu.add(0, item3, 0, "修改课程");
        menu.add(0, item4, 0, "查询课程");
        menu.add(0, item5, 0, "显示课程");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SecCouSurface.this);
        switch (item.getItemId()) {
            case item1:
                Intent intent1 = new Intent();
                intent1.setClass(SecCouSurface.this, SecAddCou.class);
                SecCouSurface.this.startActivity(intent1);
                break;
            case item2:
                Intent intent2 = new Intent();
                intent2.setClass(SecCouSurface.this, SecDeleteCou.class);
                SecCouSurface.this.startActivity(intent2);
                break;
            case item3:
                Intent intent3 = new Intent();
                intent3.setClass(SecCouSurface.this, SecModifyCou.class);
                SecCouSurface.this.startActivity(intent3);
                break;
            case item4:
                Intent intent4 = new Intent();
                intent4.setClass(SecCouSurface.this, SecSearchCou.class);
                SecCouSurface.this.startActivity(intent4);
                break;
            case item5:
                Intent intent5 = new Intent();
                intent5.setClass(SecCouSurface.this, SecDisplayCou.class);
                SecCouSurface.this.startActivity(intent5);
                break;
        }
        return true;
    }

    class okClick implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    }


    /**
     * 双击返回键退出
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mIsExit) {
//                this.finish();
//                System.exit(0);
//            } else {
//                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
//                mIsExit = true;
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mIsExit = false;
//                    }
//                }, 2000);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}