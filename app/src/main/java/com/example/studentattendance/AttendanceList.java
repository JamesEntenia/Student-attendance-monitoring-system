package com.example.studentattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class AttendanceList extends AppCompatActivity{

    TextView Welcometv;
    CheckBox ACB,PCB;

    ListView Attendance_ListView;
    MySQL db;
    ArrayList<String> a = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list2);
        getSupportActionBar().hide();

        Attendance_ListView = (ListView) findViewById(R.id.Attendance_ListView);
        Welcometv = (TextView) findViewById(R.id.WelcomeTV);
        ACB = (CheckBox) findViewById(R.id.AbsentCB);
        PCB = (CheckBox) findViewById(R.id.PresentCB);

        db = new MySQL(this);

        LoginedAccount();
        RegisteredNames();
    }

    public void LoginedAccount(){

        Intent intent = getIntent();
        String HelloName = intent.getStringExtra("com.example.studentattendance.HelloName");

        Welcometv.setText("Hello "+HelloName+"!");
    }

    public void RegisteredNames()
    {
        Cursor RegisteredAcc = db.ViewAccounts();
        while(RegisteredAcc.moveToNext())
        {
            a.add(RegisteredAcc.getString(0));

        }

        ItemAdapter item = new ItemAdapter(AttendanceList.this,a);
        Attendance_ListView.setAdapter(item);
        Attendance_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    startActivity(new Intent(AttendanceList.this,Register.class));
            }
        });
    }

}
