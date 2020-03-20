package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.appbar.AppBarLayout;
import androidx.appcompat.widget.Toolbar;

public class SocialMedia extends AppCompatActivity {
Toolbar toolbar;
    ViewPager viewPager;
        TabLayout tableLayout;
    tabadapter tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        setTitle("Soical media");

        toolbar=findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        viewPager=findViewById(R.id.viewPager);
        tab=new tabadapter(getSupportFragmentManager());
        viewPager.setAdapter(tab);

        tableLayout=findViewById(R.id.tabLayout);
        tableLayout.setupWithViewPager(viewPager,false);
    }
}
