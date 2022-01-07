package com.androidp.homesearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText log_u_name, log_passwd;
    private Button log_btn;
    private TextView reg_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        log_u_name = findViewById(R.id.log_u_name);
        log_passwd = findViewById(R.id.log_passwd);
        log_btn = findViewById(R.id.log_btn);
        reg_btn = findViewById(R.id.reg_btn);

        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(log_u_name.getText().toString().equals("shreeya") && log_passwd.getText().toString().equals("123")){
                    Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeScreen.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Invalid Credentials!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisActivity.class);
                startActivity(intent);
            }
        });

    }
}