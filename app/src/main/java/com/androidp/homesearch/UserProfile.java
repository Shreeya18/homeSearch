package com.androidp.homesearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {


    FirebaseUser user;
    private DatabaseReference reference;
    private String userid;
    private TextView profUser, profEmail, profContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();

        profContact = findViewById(R.id.profContact);
        profEmail = findViewById(R.id.profEmail);
        profUser = findViewById(R.id.profileUname);

        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprof = snapshot.getValue(User.class);
                if(userprof != null){
                    String name = userprof.name;
                    String email = userprof.email;
                    String contact = userprof.contact;

                    profUser.setText(name);
                    profEmail.setText(email);
                    profContact.setText(contact);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UserProfile.this,  "Something went Wrong!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}