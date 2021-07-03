package com.example.studentattendance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    Button Register;
    EditText Username,Password,ConfirmPassword,Question,Answer;
    MySQL Teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        Teacher = new MySQL(this);
        Register = (Button) findViewById(R.id.Registerbtn);
        Username = (EditText) findViewById((R.id.UsernamePT));
        Password = (EditText) findViewById((R.id.PasswordPT));
        ConfirmPassword = (EditText) findViewById((R.id.ConfirmPasswordPT));
        Question = (EditText) findViewById((R.id.QuestionPT));
        Answer = (EditText) findViewById((R.id.AnswerPT));

        AccountRegistration();

    }

    public void AccountRegistration()
    {
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor RegisteredAcc = Teacher.ViewAccounts();
                boolean Existing = false;

                if (Username.getText().toString().isEmpty() || Password.getText().toString().isEmpty() || Question.getText().toString().isEmpty() || Answer.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "You must fill all requirments", Toast.LENGTH_LONG).show();
                }
                else if(!Password.getText().toString().equals(ConfirmPassword.getText().toString())){
                    Toast.makeText(Register.this,"Password didn't match",Toast.LENGTH_LONG).show();
                    ConfirmPassword.setText(null);

                }
                else {
                    while (RegisteredAcc.moveToNext()) {
                        if (RegisteredAcc.getString(0).equals(Username.getText().toString())) {

                            Existing = true;
                            break;
                        }
                    }

                    if (!Existing) {
                        Teacher.Register(Username.getText().toString(), Password.getText().toString(), Question.getText().toString(), Answer.getText().toString());
                        Toast.makeText(Register.this, "Data Inserted", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), AttendanceList.class);
                        intent.putExtra("com.example.studentattendance.HelloName",Username.getText().toString());
                        startActivity(intent);

                        Username.setText(null);
                        Password.setText(null);
                        ConfirmPassword.setText(null);
                        Question.setText(null);
                        Answer.setText(null);

                    } else {
                        Toast.makeText(Register.this, "Account is Already Exist", Toast.LENGTH_LONG).show();
                        Username.setText(null);
                    }
                }
            }
        });
    }
}
