package com.beacons.railwayaid.raid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    ViewPager viewPager;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPager();
        initTabHost();


    }

    private void initTabHost() {
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        String []tabNames = {"Trains", "Navigation", "Notifications"};

        for(int i=0; i<tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }

    public class FakeContent implements TabHost.TabContentFactory{

        Context context;

        public FakeContent(Context mcontext){
            context = mcontext;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumHeight(0);
            return fakeView;
        }
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        List<Fragment> listFragments = new ArrayList<>();
        listFragments.add(new Trains());
        listFragments.add(new Navigation());
        listFragments.add(new Notifications());

        PagerAdapter pagerAdapter = new PagerAdapter(
                getSupportFragmentManager(), listFragments
        );

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onTabChanged(String tabId){
        int selectedItem = tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);
        HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.h_scroll_view);

        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft()-(hScrollView.getWidth()-tabView.getWidth())/2;
        hScrollView.smoothScrollTo(scrollPos, 0);
    }

    @Override
    public void onPageScrollStateChanged(int arg0){

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2){

    }

    @Override
    public void onPageSelected(int selectedItem){
        tabHost.setCurrentTab(selectedItem);
    }
}
