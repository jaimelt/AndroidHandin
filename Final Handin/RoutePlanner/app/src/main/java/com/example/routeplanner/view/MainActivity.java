package com.example.routeplanner.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.routeplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private HomeFragment homeFragment;
    private RoutesFragment routesFragment;
    private MyRoutesFragment myRoutesFragment;
    private FavoriteFragment favoriteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_bar);
        frameLayout = findViewById(R.id.frame);

        homeFragment = new HomeFragment();
        routesFragment = new RoutesFragment();
        myRoutesFragment = new MyRoutesFragment();
        favoriteFragment = new FavoriteFragment();

        setFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_routes:
                        setFragment(routesFragment);
                        return true;

                    case R.id.nav_my_routes:
                        setFragment(myRoutesFragment);
                        return true;
                    case R.id.nav_favorite:
                        setFragment(favoriteFragment);
                }
                return true;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
