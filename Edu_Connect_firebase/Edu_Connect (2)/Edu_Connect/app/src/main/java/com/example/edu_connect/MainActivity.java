package com.example.edu_connect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity<drawer, navigationView> extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    //variables for dashboard
    public CardView card1,card2,card3,card4;
    //variables for sidebar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null)
        {
            Intent intent = new Intent(this,login.class);
            startActivity(intent);
            finish();
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentUser.getUid());


        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
         //linking cardview to variables
        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);
        card4 = (CardView) findViewById(R.id.c4);
        //setting on click listener
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        //FINDING LAYOUT MENU BY ID'S
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navi_view);
        toolbar1 = findViewById(R.id.toolbar);
        //SETTING CUSTOME TOOL BAR
        setSupportActionBar(toolbar1);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar1,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //making menu clickable
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            FirebaseAuth.getInstance().signOut();
            super.onBackPressed();
        }

    }

    //adding onclick method for click event dashboard
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId())
        {
            case R.id.c1:
            {
                i = new Intent(this,Chat.class);
                startActivity(i);
                break;
            }
            case R.id.c2:
            {
                i = new Intent(this,Timetable.class);
                startActivity(i);
                break;
            }
            case R.id.c3:
            {
                i = new Intent(this,Notes.class);
                startActivity(i);
                break;
            }
            case R.id.c4:
            {
                i = new Intent(this,Cal.class);
                startActivity(i);
                break;
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.Profile:
            {
                Intent i = new Intent(this,profiledemo.class);
                startActivity(i);
                break;
            }
            case R.id.logout:
            {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this,login.class);
                startActivity(i);
                finish();

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}