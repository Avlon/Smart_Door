package com.example.zhao.smart_doors.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewManager;

import com.example.zhao.smart_doors.Adapter.PageAdapter;
import com.example.zhao.smart_doors.Fragment.LockFragment;
import com.example.zhao.smart_doors.Fragment.MonitorFragment;
import com.example.zhao.smart_doors.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/4/12.
 */
public class MainActivity extends AppCompatActivity {
    private PageAdapter pageAdapter;
    private List<Fragment> page_list;
    private ViewPager viewPager;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        viewPager=(ViewPager)findViewById(R.id.pagerview);

        page_list=new ArrayList<Fragment>();

        page_list.add(new MonitorFragment());
        page_list.add(new LockFragment());

        pageAdapter=new PageAdapter(getSupportFragmentManager(),page_list);

        viewPager.setAdapter(pageAdapter);
    }
}
