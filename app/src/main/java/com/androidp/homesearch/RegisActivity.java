package com.androidp.homesearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisActivity extends AppCompatActivity {

    private EditText reg_name, reg_email, reg_contact, reg_passwd;
    private Button signinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);


        reg_name = findViewById(R.id.reg_name);
        reg_email = findViewById(R.id.reg_email);
        reg_contact = findViewById(R.id.reg_contact);
        reg_passwd = findViewById(R.id.reg_passwd);
        signinBtn = findViewById(R.id.signinBtn);



        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (reg_name.getText().toString().isEmpty() || reg_email.getText().toString().isEmpty() ||
                            reg_contact.getText().toString().isEmpty() || reg_passwd.getText().toString().isEmpty()) {

                    Toast.makeText(RegisActivity.this, "Enter Credentials",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}