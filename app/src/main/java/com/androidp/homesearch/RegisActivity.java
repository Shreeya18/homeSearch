package com.androidp.homesearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisActivity extends AppCompatActivity {

    private EditText reg_name, reg_email, reg_contact, reg_passwd;
    private Button register;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);


        reg_name = findViewById(R.id.reg_name);
        reg_email = findViewById(R.id.reg_email);
        reg_contact = findViewById(R.id.reg_contact);
        reg_passwd = findViewById(R.id.reg_passwd);
        register = findViewById(R.id.signinBtn);


        auth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });


    }




    // User Registration Method! with Firebase
    public void registerUser(){
        String email = reg_email.getText().toString();
        String passwd = reg_passwd.getText().toString();
        String name = reg_name.getText().toString();
        String contact = reg_contact.getText().toString();

        // Checks if fields are null
        if(name.isEmpty()){
            reg_name.setError("Name is Required");
            reg_name.requestFocus();
            return;
        }
        if(passwd.isEmpty()){
            reg_passwd.setError("Password is Required");
            reg_passwd.requestFocus();
            return;
        }
        if(email.isEmpty()){
            reg_email.setError("Email is Required");
            reg_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            reg_email.setError("Invalid Email ID");
            reg_email.requestFocus();
            return;
        }

        if(contact.isEmpty()){
            reg_contact.setError("Mobile Number is Required");
            reg_contact.requestFocus();
        }


        //Authenticate user with Email and Password
        auth.createUserWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(name, email, passwd, contact);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisActivity.this, "Success Registration!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RegisActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}