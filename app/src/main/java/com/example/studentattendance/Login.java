package com.example.studentattendance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText Password,Username;
    Button Login;
    TextView Register,Recover;
    CheckBox A,B;

    MySQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Password =(EditText) findViewById(R.id.PasswordPT);
        Username =(EditText) findViewById(R.id.UsernamePT);
        Register = (TextView) findViewById(R.id.Register);
        Recover = (TextView) findViewById(R.id.Recover);
        Login = (Button) findViewById(R.id.Loginbtn);


        db = new MySQL(this);
        Register();
        Login();
        A();
        B();

    }

    public void A(){
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(B.isChecked()){
                    B.toggle();
                }

            }
        });
    }

    public void B(){
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(A.isChecked()){
                    A.toggle();
                }


            }
        });
    }

    public void Register(){
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }


    double[][] a = {{}};
    public void Login(){
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean AccountExist = false;
                Cursor RegisteredAcc = db.ViewAccounts();

                while(RegisteredAcc.moveToNext())
                {
                    if(Username.getText().toString().equals(RegisteredAcc.getString(0)) && Password.getText().toString().equals(RegisteredAcc.getString(1))){
                        AccountExist = true;
                    break;
                    }
                }
                if(AccountExist){
                    Intent intent = new Intent(getApplicationContext(),AttendanceList.class);
                    intent.putExtra("com.example.studentattendance.HelloName",Username.getText().toString());
                    startActivity(intent);

                }
                else{
                    Toast.makeText(Login.this,"Invalid Account",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
