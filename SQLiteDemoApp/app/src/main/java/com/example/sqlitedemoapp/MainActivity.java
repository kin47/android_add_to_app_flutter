package com.example.sqlitedemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.sqlitedemoapp.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.view_pager);
        floatingActionButton = findViewById(R.id.floating_action_button);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNav.getMenu().findItem(R.id.navigation_home).setChecked(true);
                        break;
                    case 1:
                        bottomNav.getMenu().findItem(R.id.navigation_history).setChecked(true);
                        break;
                    case 2:
                        bottomNav.getMenu().findItem(R.id.navigation_search).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.navigation_history:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.navigation_search:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FlutterActivity.class);
            startActivity(intent);
        });
    }
}