package com.example.arguide_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    FragmentManager fm;
    FragmentTransaction ft;
    ArguideFrag arguideFrag;
    MainFrag mainFrag;
    NavigationFrag navigationFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*fm = getSupportFragmentManager();
        ft = fm.beginTransaction();*/

        bottomNavigationView = findViewById(R.id.bottomNV);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_main:
                        Toast.makeText(MainActivity.this, "메인", Toast.LENGTH_SHORT).show();
                        setFrag(0);
                        break;
                    case R.id.menu_navigation:
                        Toast.makeText(MainActivity.this, "길찾기", Toast.LENGTH_SHORT).show();
                        setFrag(1);
                        break;
                    case R.id.menu_arguide:
                        Toast.makeText(MainActivity.this, "AR가이드", Toast.LENGTH_SHORT).show();
                        setFrag(2);
                        break;
                }
                return false;
            }
        });
        mainFrag = new MainFrag();
        navigationFrag = new NavigationFrag();
        arguideFrag = new ArguideFrag();




        setFrag(0);

    }
    private void setFrag(int frag){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        switch (frag){
            case 0:
                ft.replace(R.id.frame_main , mainFrag);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.frame_main , navigationFrag);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.frame_main , arguideFrag);
                ft.commit();
                break;
        }
    }
}