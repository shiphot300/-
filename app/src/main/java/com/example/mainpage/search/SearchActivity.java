package com.example.mainpage.search;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mainpage.R;
import com.google.android.material.tabs.TabLayout;

public class SearchActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SearchPagerAdapter mPagerAdapter;
    private ImageView mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serarch);

        mTabLayout = findViewById(R.id.search_tab);
        mViewPager = findViewById(R.id.pager);
        mBtnBack = findViewById(R.id.iv_back);

        initViewPager();
        initTabLayout();
    }

    private void initViewPager(){
        mPagerAdapter = new SearchPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void initTabLayout(){

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText("최근검색어");
        mTabLayout.getTabAt(1).setText("인기검색어");
    }


    static class SearchPagerAdapter extends FragmentPagerAdapter {


        public SearchPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    return LastSearchFragment.newInstance();
                case 1:
                    return PopularSearchFragment.newInstance();
            }
            return fragment;

        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
