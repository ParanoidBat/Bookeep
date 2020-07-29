package com.example.android.bookeep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class ProjectActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 2;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        viewPager = findViewById(R.id.vp_project);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return AddProjectFragment.newInstance();
                case 1:
                    return ViewProjectsFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "Add Project";
                case 1:
                    return "View Projects";
                default:
                    return null;
            }
        }
    }
}
