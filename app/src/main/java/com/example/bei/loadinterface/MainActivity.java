package com.example.bei.loadinterface;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private SQLiteDatabase mSqLiteDatabase;
    ContentValues mContentValues = new ContentValues();
    private LoginPage mLoginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        mSqLiteDatabase = databaseHelper.getWritableDatabase();
        // 获取登录名
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String userName = bundle.getString("loginUserName");
        Button signOutButton = (Button) findViewById(R.id.sign_out);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 更新数据库登陆状态
                mContentValues.put(DatabaseHelper.LOGIN_STATE, "false");
                String whereClauseString = "username=?";
                String[] whereArgsString = {userName};
                mSqLiteDatabase.update(DatabaseHelper.USER_TABLE_NAME, mContentValues, whereClauseString, whereArgsString);
                Toast.makeText(MainActivity.this, "账号已登出", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginPage.class));
                finish();
            }
        });

    }
}
