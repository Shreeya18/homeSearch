package com.androidp.homesearch;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView nav_view;
    Toolbar tool_bar;
    ActionBarDrawerToggle toggle;
    ProgressBar pro_bar;


    // Recycler View ************************************ Show Data ****************************'
    private RecyclerView recyview;
    HouseAdapter houseAdapter;
    ArrayList<FirebaseModal> list;
    private FirebaseDatabase storage = FirebaseDatabase.getInstance();
    DatabaseReference myRef = storage.getReference().child("Owners");
    StorageReference db = FirebaseStorage.getInstance().getReference();
    // ******************  Show Data ************************************************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        drawerLayout = findViewById(R.id.draw_lay);
        nav_view = findViewById(R.id.nav_view);
        tool_bar = findViewById(R.id.tool_bar);
        pro_bar = findViewById(R.id.pro_bar);

        // **********************************************
        recyview = findViewById(R.id.recyview);
        recyview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        houseAdapter = new HouseAdapter(this, list);
        recyview.setAdapter(houseAdapter);

        tool_bar.setTitle("");
        pro_bar.setVisibility(View.VISIBLE);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    FirebaseModal firebaseModal = dataSnapshot.getValue(FirebaseModal.class);
                    list.add(firebaseModal);
                    pro_bar.setVisibility(View.GONE);
                }
                houseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pro_bar.setVisibility(View.GONE);
                Log.d("DatabaseError", "The Error is: " + error);
                Toast.makeText(HomeScreen.this, "Kindly check Your Conectivity", Toast.LENGTH_SHORT).show();
            }
        });


        // **********************************************


        nav_view.setNavigationItemSelectedListener(this);
        setSupportActionBar(tool_bar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeScreen.this,drawerLayout,tool_bar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.price_pred){
            Intent intent = new Intent(HomeScreen.this, PricePredict.class);
            startActivity(intent);
        }
        if(id == R.id.login){
            Intent intent = new Intent(HomeScreen.this,LoginActivity.class);
            startActivity(intent);
        }
        if(id == R.id.addnewP){
            Intent intent = new Intent(HomeScreen.this, AddProperty.class);
            startActivity(intent);
        }
        if(id == R.id.about){
            Intent intent = new Intent(HomeScreen.this, About.class);
            startActivity(intent);
        }
        return false;
    }


}