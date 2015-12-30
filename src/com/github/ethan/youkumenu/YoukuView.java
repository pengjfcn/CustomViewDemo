package com.github.ethan.youkumenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.ethan.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

public class YoukuView extends RelativeLayout implements OnClickListener {
	private static final String TAG = "YoukuView";
	private ImageView mIvHome;
	private ImageView mIvMenu;
	private RelativeLayout mRlLevel1container;
	private RelativeLayout mRlLevel2container;
	private RelativeLayout mRlLevel3container;

	// flag
	private boolean isLevel1Display = true;
	private boolean isLevel2Display = true;
	private boolean isLevel3Display = true;
	
	//isAnim count 
	private int animCount;

	public YoukuView(Context context) {
		super(context, null);
	}

	public YoukuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// inflate the layout
		View.inflate(context, R.layout.view_youku, this);
		//set window focused get activity event
		setFocusableInTouchMode(true);
		initView();
		initEvent();
	}

	private void initView() {
		mIvHome = (ImageView) findViewById(R.id.home);
		mIvMenu = (ImageView) findViewById(R.id.menu);
		mRlLevel1container = (RelativeLayout) findViewById(R.id.level1);
		mRlLevel2container = (RelativeLayout) findViewById(R.id.level2);
		mRlLevel3container = (RelativeLayout) findViewById(R.id.level3);
	}

	private void initEvent() {
		// click home
		mIvHome.setOnClickListener(this);
		// click menu
		mIvMenu.setOnClickListener(this);
	}
	//hardware key
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_MENU){
			// if level is animating click  is false
			if (animCount > 0) {
				return true;
			}
			if (isLevel1Display && isLevel2Display && isLevel3Display) {
				dismissAnim(mRlLevel1container, 100);
				dismissAnim(mRlLevel2container, 50);
				dismissAnim(mRlLevel3container, 0);
				isLevel1Display = false;
				isLevel2Display = false;
				isLevel3Display = false;
				return true;
			}
			if(!isLevel1Display && !isLevel2Display && !isLevel3Display){
				displayAnim(mRlLevel1container, 0);
				displayAnim(mRlLevel2container, 50);
				displayAnim(mRlLevel3container, 100);
				isLevel1Display = true;
				isLevel2Display = true;
				isLevel3Display = true;
				return true;
			}
			if (isLevel1Display && !isLevel2Display && !isLevel3Display) {
				dismissAnim(mRlLevel1container, 0);

				isLevel1Display = false;
				return true;
			}
		}
		return super.onKeyUp(keyCode, event); //default false
	}

	@Override
	public void onClick(View v) {
		// if level is animating click  is false
		if (animCount > 0) {
			return;
		}
		if (v == mIvHome) {
			// level1-level3 open : dismiss all
			if (isLevel1Display && isLevel2Display && isLevel3Display) {
				dismissAnim(mRlLevel1container,100);
				dismissAnim(mRlLevel2container,50);
				dismissAnim(mRlLevel3container,0);
				
				isLevel1Display = false;
				isLevel2Display = false;
				isLevel3Display = false;
				return;
			}
			// level1-level3 close : display all
			if (!isLevel1Display && !isLevel2Display && !isLevel3Display) {
				displayAnim(mRlLevel1container,100);
				displayAnim(mRlLevel2container,50);
				displayAnim(mRlLevel3container,0);
				
				isLevel1Display = true;
				isLevel2Display = true;
				isLevel3Display = true;
				return;
			}
			if (isLevel1Display && !isLevel2Display && !isLevel3Display) {
				displayAnim(mRlLevel2container,0);
				
				isLevel2Display = true;
				return;
			}
			if (isLevel1Display && isLevel2Display && !isLevel3Display) {
				dismissAnim(mRlLevel2container,0);
				
				isLevel2Display = false;
				return;
			}
		} else if (v == mIvMenu) {
			if (isLevel1Display && isLevel2Display && !isLevel3Display) {
				displayAnim(mRlLevel3container,0);
				
				isLevel3Display = true;
				return;
			}
			if (isLevel1Display && isLevel2Display && isLevel3Display) {
				dismissAnim(mRlLevel3container,0);
				
				isLevel3Display = false;
				return;
			}
		}
	}
	
	//action animation
	public void displayAnim(RelativeLayout container,long startDelay){
		// rotate animation  from 0 to 180
		ViewHelper.setPivotX(container,container.getWidth()/2);
		ViewHelper.setPivotY(container,container.getHeight());
		ObjectAnimator animator = ObjectAnimator.ofFloat(container, "rotation", -180,0);
		animator.setDuration(500);
		animator.setStartDelay(startDelay);
		animator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				animCount++;
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				animCount--;
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				
			}
		});
		animator.start();
	}
	
	//action animation
	public void dismissAnim(RelativeLayout container,long startDelay){
		// rotate animation  from 0 to 180
		ViewHelper.setPivotX(container,container.getWidth()/2);
		ViewHelper.setPivotY(container,container.getHeight());
		ObjectAnimator animator = ObjectAnimator.ofFloat(container, "rotation", 0,-180);
		animator.setDuration(500);
		animator.setStartDelay(startDelay);
		animator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator arg0) {
				animCount++;
			}
			
			@Override
			public void onAnimationRepeat(Animator arg0) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator arg0) {
				animCount--;
			}
			
			@Override
			public void onAnimationCancel(Animator arg0) {
				
			}
		});
		animator.start();
	}
	

}
