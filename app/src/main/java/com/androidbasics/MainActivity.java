package com.androidbasics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    TabLayout tabLayout;
    TabItem tab1, tab2, tab3;
    ViewPager viewPager;
    FraManager fraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("Your Messenger");
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tLayout);
        tab1 = findViewById(R.id.chatTab);
        tab2 = findViewById(R.id.StatTab);
        tab3 = findViewById(R.id.callTab);
        viewPager = findViewById(R.id.vPager);


        drawerLayout = findViewById(R.id.myDrawer);
        navigationView = findViewById(R.id.drawerNav);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        fraManager = new FraManager(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount());
        viewPager.setAdapter(fraManager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home)
                    Toast.makeText(getApplicationContext(), "Home is clicked", Toast.LENGTH_LONG).show();

                if (item.getItemId() == R.id.call)
                    Toast.makeText(getApplicationContext(), "Call is clicked", Toast.LENGTH_LONG).show();

                if (item.getItemId() == R.id.setting)
                    Toast.makeText(getApplicationContext(), "Setting is clicked", Toast.LENGTH_LONG).show();

                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });


    }
}