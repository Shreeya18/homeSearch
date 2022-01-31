package com.androidp.homesearch;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView nav_view;
    Toolbar tool_bar;
    ActionBarDrawerToggle toggle;

    private Button propAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        drawerLayout = findViewById(R.id.draw_lay);
        nav_view = findViewById(R.id.nav_view);
        tool_bar = findViewById(R.id.tool_bar);

        propAdd = findViewById(R.id.prop_add);


        propAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, AddProperty.class);
                startActivity(intent);
            }
        });

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
        return false;
    }


}