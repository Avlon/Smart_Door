package com.example.zhao.smart_doors.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.Window;

import com.example.zhao.smart_doors.R;

/**
 * Created by zhao on 2016/4/15.
 */
public class MonitorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
        @Override
        public void onCreate(Bundle savedInstanceState) {
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity__main_monitor);
        }
        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
                return false;
        }
}
