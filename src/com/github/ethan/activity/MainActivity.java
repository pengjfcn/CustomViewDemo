package com.github.ethan.activity;

import com.github.ethan.R;
import com.github.ethan.R.layout;
import com.github.ethan.R.menu;
import com.github.ethan.spinner.SpinnerActivity;
import com.github.ethan.sweep.SweepActivity;
import com.github.ethan.switchtoggle.SwitchActivity;
import com.github.ethan.viewpager.ViewPagerActivity;
import com.github.ethan.youkumenu.YoukuActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity  {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.tv_viewpager).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,ViewPagerActivity.class));
			}
		});
		findViewById(R.id.tv_sliding).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,ViewPagerActivity.class));
			}
		});
		findViewById(R.id.tv_spinner).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,SpinnerActivity.class));
			}
		});
		findViewById(R.id.tv_sweep).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,SweepActivity.class));
			}
		});
		findViewById(R.id.tv_youku).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,YoukuActivity.class));
			}
		});
		findViewById(R.id.tv_switch).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,SwitchActivity.class));
			}
		});
	}

}
