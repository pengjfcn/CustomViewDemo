package com.github.ethan.youkumenu;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.ethan.R;

public class YoukuView extends RelativeLayout implements OnClickListener {
	private ImageView mIvHome;
	private ImageView mIvMenu;
	private RelativeLayout mLevel1;
	private RelativeLayout mLevel2;
	private RelativeLayout mLevel3;

	// flag
	private boolean isLevel1 = true;
	private boolean isLevel2 = true;
	private boolean isLevel3 = true;

	public YoukuView(Context context) {
		super(context, null);
	}

	public YoukuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// inflate the layout
		View.inflate(context, R.layout.view_youku, this);
		initView();
		initEvent();
	}

	private void initView() {
		mIvHome = (ImageView) findViewById(R.id.home);
		mIvMenu = (ImageView) findViewById(R.id.menu);
		mLevel1 = (RelativeLayout) findViewById(R.id.level1);
		mLevel2 = (RelativeLayout) findViewById(R.id.level2);
		mLevel3 = (RelativeLayout) findViewById(R.id.level3);
	}

	private void initEvent() {
		// click home
		mIvHome.setOnClickListener(this);
		// click menu
		mIvMenu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == mIvHome) {
			// level1-level3 open : dismiss level2 level3
			if (isLevel1 && isLevel2 && isLevel3) {
//				mLevel2.setVisibility(View.GONE);
				displayAnim(mLevel2);
//				mLevel3.setVisibility(View.GONE);
				displayAnim(mLevel3);
				
				isLevel2 = false;
				isLevel3 = false;
				return;
			}
			// level2-level3 close : display level2
			if (isLevel1 && !isLevel2 && !isLevel3) {
//				mLevel2.setVisibility(View.VISIBLE);
				displayAnim(mLevel2);
				
				isLevel2 = true;
				return;
			}
			// level1 level2 open : dismiss level2
			if (isLevel1 && isLevel2 && !isLevel3) {
				mLevel2.setVisibility(View.GONE);
				displayAnim(mLevel2);
				
				isLevel2 = false;
				return;
			}
		} else if (v == mIvMenu) {
			// level1 level2 open : display level3
			if (isLevel1 && isLevel2 && !isLevel3) {
				mLevel3.setVisibility(View.VISIBLE);
				displayAnim(mLevel3);
				
				isLevel3 = true;
				return;
			}
			// level1 level2 level3 open : dismiss level3
			if (isLevel1 && isLevel2 && isLevel3) {
				mLevel3.setVisibility(View.GONE);
				displayAnim(mLevel3);
				
				isLevel3 = false;
				return;
			}
		}
	}
	
	//action animation
	public void displayAnim(View v){
		// rotate animation  from 0 to 180
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotation", -180,0);
		animator.setDuration(500);
		animator.start();
	}
	
	//action animation
	public void dismissAnim(View v){
		// rotate animation  from 0 to 180
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotation", 0,-180);
		animator.setDuration(500);
		animator.start();
	}
	

}
