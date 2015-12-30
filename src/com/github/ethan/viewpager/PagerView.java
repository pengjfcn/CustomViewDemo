package com.github.ethan.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ethan.R;
import com.github.ethan.activity.MainActivity;

public class PagerView extends RelativeLayout {
	private ViewPager mPager;
	private TextView mTvTitle;
	private LinearLayout mDotContainer;
	private PagerAdapter mAdapter;
	private Button mBtn;
	// data
	private List<ImageView> mDatas;
	private String[] title = new String[] { "艾欧尼亚", "洛克萨斯", "德玛西亚" };
	private int[] map = new int[] { R.drawable.anni, R.drawable.jase,
			R.drawable.zed };

	public PagerView(Context context) {
		this(context, null);
	}

	public PagerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// load xml
		View.inflate(context, R.layout.view_viewpager, this);

		initView();
		initData();
		initEvent();
		
		//infinite
//		int item = Integer.MAX_VALUE / 2;
//		item = item - item %mDatas.size();
//		mPager.setCurrentItem(item);
	}

	private void initView() {
		mPager = (ViewPager) findViewById(R.id.view_pager);
		mTvTitle = (TextView) findViewById(R.id.title);
		mDotContainer = (LinearLayout) findViewById(R.id.dots_container);
		mBtn = (Button) findViewById(R.id.login);
	}

	private void initData() {
		mDatas = new ArrayList<ImageView>();
		for (int i = 0; i < map.length; i++) {
			ImageView view = new ImageView(getContext());
			// set data
			view.setImageResource(map[i]);
			// scale
			view.setScaleType(ScaleType.FIT_XY);
			mDatas.add(view);

			//  load dot
			View dot = new View(getContext());
			dot.setBackgroundResource(R.drawable.dot_normal);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					10, 10);
			if (i != 0) {
				params.leftMargin = 10;
			} else {
				// default selc
				dot.setBackgroundResource(R.drawable.dot_select);
				mTvTitle.setText(title[i]);
			}
			mDotContainer.addView(dot, params);
		}
		mAdapter = new MyAdapter();
		mPager.setAdapter(mAdapter);
	}

	private void initEvent() {
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				//infinite
//				position = position % mDatas.size();  
				
				int count = getChildCount();
				for (int i = 0; i <= count; i++) {
					View view = mDotContainer.getChildAt(i);
					view.setBackgroundResource(i == position ? R.drawable.dot_select
							: R.drawable.dot_normal);
					mBtn.setVisibility(position == mDatas.size() - 1 ? View.VISIBLE
							: View.GONE);
				}
				mTvTitle.setText(title[position]);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		mBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "进入主页", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getContext(), MainActivity.class);
				// The desired flags. 
				// clear after finish
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				getContext().startActivity(intent);
			}
		});
	}

	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			if (mDatas != null) {
				// return mDatas.size();
				return Integer.MAX_VALUE;
			}
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			//infinite
//			position = position % mDatas.size();
			
			ImageView imageView = mDatas.get(position);
			// load view to container中
			container.addView(imageView);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// remove current
			container.removeView((View) object);
		}

	}
}
