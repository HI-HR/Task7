package com.example.task7.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.task7.FragmnetFirst.FirstFragment;
import com.example.task7.FragmnetFirst.SecondFragment;
import com.example.task7.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private TabLayout mTab ;
    private ViewPager2 vp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        ArrayList<FragmentInterface> mFragments= new ArrayList<>();

        mFragments.add(new FragmentInterface() {
            @Override
            public Fragment back() {
                return new FirstFragment();
            }
        });
        mFragments.add(new FragmentInterface() {
            @Override
            public Fragment back() {
                return new SecondFragment();
            }
        });

        vp2.setAdapter(new Vp2Adapter(this,mFragments));

        new TabLayoutMediator(mTab, vp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position==0){
                    tab.setText("首页");
                }else{
                    tab.setText("教程");
                }
            }
        }).attach();



    }


    private void initView() {
        mTab=findViewById(R.id.tab_tablayout);
        vp2=findViewById(R.id.vp2_home);
    }
}