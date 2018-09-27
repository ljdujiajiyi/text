package com.example.dell_pc.qinyongcongyuekao;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dell_pc.qinyongcongyuekao.fragment.Fragmentone;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private String[] mTitle={"关注","推荐","热点","北京","视频","新时代"};
private List<Fragment> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
       TabLayout  mTablayout=findViewById(R.id.mTablayout);
       ViewPager mviewpager=findViewById(R.id.mviewpager);
      ImageView img= findViewById(R.id.img1);
      img.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,com.example.topgridlibrary.topgrid.ChannelActivity.class));
          }
      });
        for (int i=0;i<mTitle.length;i++){
            list.add(new Fragmentone());
        }
        MyAdpater myAdpater=new MyAdpater(getSupportFragmentManager());
        mviewpager.setAdapter(myAdpater);
        mTablayout.setupWithViewPager(mviewpager);
    }
    class MyAdpater extends FragmentPagerAdapter{

        public MyAdpater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }
}
