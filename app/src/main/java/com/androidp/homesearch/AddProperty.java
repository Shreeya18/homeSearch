package com.androidp.homesearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddProperty extends AppCompatActivity {

    private Button savebtn, uploadBtn;
    private ImageView addImg;
    private EditText priceAdd, bhkAdd, locAdd, sqftAdd;
    private Uri imageuri;
    private RadioGroup radiogrp;
    private RadioButton radioButton;

    // Created Firebase database connection
    private FirebaseDatabase storage = FirebaseDatabase.getInstance();
    DatabaseReference myRef = storage.getReference().child("Owners");
    StorageReference db = FirebaseStorage.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        // Initialized Variables for basic components
        savebtn = findViewById(R.id.savebtn);
        addImg = findViewById(R.id.addImg);
        uploadBtn = findViewById(R.id.uploadBtn);
        priceAdd = findViewById(R.id.priceAdd);
        bhkAdd = findViewById(R.id.bhkAdd);
        locAdd = findViewById(R.id.locAdd);
        sqftAdd = findViewById(R.id.sqftAdd);
        radiogrp = findViewById(R.id.grpbtns);

        // Clear the radioButton at first.
        radiogrp.clearCheck();

        // Check and retrieve selected Id (Button) from grp
        radiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)radioGroup.findViewById(i);
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Checked ID
                int selectedId = radiogrp.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(AddProperty.this, "No answer has been selected", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Add The text of the radio Button
                    radioButton = (RadioButton)radiogrp.findViewById(selectedId);
                }
                if(imageuri != null){
                    upload(imageuri);

                }else{
                    Toast.makeText(AddProperty.this, "Kindly Select Image", Toast.LENGTH_SHORT).show();
                }

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePic();
            }
        });

    }
    public void choosePic(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data!= null && data.getData()!= null){
            imageuri = data.getData();
            addImg.setImageURI(imageuri);
        }
    }

    private void upload(Uri uri){
        StorageReference dbr = db.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        dbr.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                dbr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        FirebaseModal fmodal = new FirebaseModal(uri.toString(), priceAdd.getText().toString(), bhkAdd.getText().toString(),
                                sqftAdd.getText().toString(), locAdd.getText().toString(), radioButton.getText().toString());
                        String  modelid = myRef.push().getKey();
                        myRef.child(modelid).setValue(fmodal);
                        Toast.makeText(AddProperty.this, "Hurrah!! Successful", Toast.LENGTH_SHORT).show();
                        priceAdd.setText("");
                        bhkAdd.setText("");
                        sqftAdd.setText("");
                        locAdd.setText("");
                        radiogrp.clearCheck();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddProperty.this, "Uploading Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getFileExtension(Uri muri){
        ContentResolver r = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(r.getType(muri));
    }
}