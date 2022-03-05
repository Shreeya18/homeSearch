package com.androidp.homesearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText log_u_name, log_passwd;
    private Button log_btn;
    private TextView reg_btn, rememe, forgotpass;
    private FirebaseAuth auth;
    private ProgressBar login_probar;
    private CheckBox chkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        log_u_name = findViewById(R.id.log_u_name);
        log_passwd = findViewById(R.id.log_passwd);
        log_btn = findViewById(R.id.log_btn);
        reg_btn = findViewById(R.id.reg_btn);
        //------------------------------------
        rememe = findViewById(R.id.rememe);
        chkbox = findViewById(R.id.chkbox);
        forgotpass = findViewById(R.id.forgotpass);
        login_probar = findViewById(R.id.login_probar);

        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkbox.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    chkbox.setChecked(true);
                    // write all the data entered by the user in SharedPreference and apply
                    myEdit.putString("name", log_u_name.getText().toString());
                    myEdit.putString("pass", log_passwd.getText().toString());
                    myEdit.apply();
                }
                login_probar.setVisibility(View.VISIBLE);
                userLogin();
            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisActivity.class);
                startActivity(intent);
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
        // ---------------- Shared Preferences --------------------------

        //sp = getSharedPreferences("Hello", Context.MODE_PRIVATE);

        // ----------------- Firebase ------------------------------------

        auth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String name = sh.getString("name", "");
        String pass = sh.getString("pass", "");

        // Setting the fetched data
        // in the EditTexts
        log_u_name.setText(name);
        log_passwd.setText(pass);
    }

    private void userLogin(){

        String email = log_u_name.getText().toString();
        String passwd = log_passwd.getText().toString();

        if(passwd.isEmpty()){
            log_passwd.setError("Password is Required");
            log_passwd.requestFocus();
            return;
        }
        if(email.isEmpty()){
            log_u_name.setError("Email is Required");
            log_u_name.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            log_u_name.setError("Invalid Email ID");
            log_u_name.requestFocus();
        }

        auth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    login_probar.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginActivity.this, HomeScreen.class);
                    intent.putExtra("email", auth.getCurrentUser().getEmail());
                    intent.putExtra("uid", auth.getCurrentUser().getUid());
                    startActivity(intent);

                }
                else{
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful Kindly Check Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}