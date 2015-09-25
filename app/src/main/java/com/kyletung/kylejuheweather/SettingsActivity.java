package com.kyletung.kylejuheweather;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //get bundle
        Intent intent = getIntent();
        ArrayList<String> cityList = intent.getStringArrayListExtra("cityList");
        final String[] list = cityList.toArray(new String[cityList.size()]);

        //init toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set textview
        TextView setCity = (TextView) findViewById(R.id.settings_set_widget_city);
        TextView authorInfo = (TextView) findViewById(R.id.settings_author_info);
        setCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setCancelable(true);
                builder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DesktopWidget.city = list[which];
                        System.out.println(list[which]);
                        Intent intent1 = new Intent();
                        intent1.setAction("com.kyletung.kylejuheweather.UPDATE_WIDGET");
                        sendBroadcast(intent1);
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        authorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setMessage("作者：KyleTung\n微博：weibo.com/kyletung\n邮箱：dyh920827@gmail.com\nGithub：github.com/KyleTung");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });
                builder.show();
            }
        });
    }

}
