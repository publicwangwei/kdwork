package com.kingdee.drc;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kingdee.drc.MyLinearLayout.OnScrollListener;

@SuppressLint("ShowToast")
public class activity_menu extends Activity implements OnTouchListener,
		GestureDetector.OnGestureListener, OnItemClickListener {

	// implements OnClickListener ,OnTouchListener, OnFocusChangeListener,
	// OnKeyListener
	// {

	private Button imageButton_t1;
	private Button imageButton_t2;
	private Button imageButton_t3;
	private Button imageButton_t4;
	private Button imageButton_t5;
	private Button imageButton_t6;

	private boolean hasMeasured = false;//
	private LinearLayout layout_left;//
	private LinearLayout layout_right;//
	private ImageView iv_set;//
	private ListView lv_set;//
	/**  */
	private int MAX_WIDTH = 0;
	/**  */
	private final static int SPEED = 30;
	private final static int sleep_time = 5;
	private GestureDetector mGestureDetector;
	private boolean isScrolling = false;
	private float mScrollX;
	private int window_width;
	private String TAG = "jj";
	private View view = null;
	private String title[] = { "  -我要添加项目汇报", "  -我要添加项目周报", "  -我要添加项目发包",
			"  -我要添加项目提成", "  -我要添加外包申请", "  -我要添加项目协同" };
	private Map<Integer, String> titles = new HashMap<Integer, String>();
	private MyLinearLayout mylaout;

	@SuppressWarnings("deprecation")
	void InitView() {
		layout_left = (LinearLayout) findViewById(R.id.layout_left);
		layout_right = (LinearLayout) findViewById(R.id.layout_right);
		iv_set = (ImageView) findViewById(R.id.iv_set);
		lv_set = (ListView) findViewById(R.id.lv_set);
		mylaout = (MyLinearLayout) findViewById(R.id.mylaout);
		// lv_set.setAdapter(new ArrayAdapter<String>(this, R.layout.item,
		// R.id.tv_item, title));
		lv_set.setAdapter(new MyAdapter(this));
		mylaout.setOnScrollListener(new OnScrollListener() {
			@Override
			public void doScroll(float distanceX) {
				doScrolling(distanceX);
			}

			@Override
			public void doLoosen() {
				RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
						.getLayoutParams();
				Log.e("jj", "layoutParams.leftMargin="
						+ layoutParams.leftMargin);
				//
				if (layoutParams.leftMargin < -window_width / 2) {
					new AsynMove().execute(-SPEED);
				} else {
					new AsynMove().execute(SPEED);
				}
			}
		});

		//
		lv_set.setOnItemClickListener(this);

		layout_right.setOnTouchListener(this);
		layout_left.setOnTouchListener(this);
		iv_set.setOnTouchListener(this);
		mGestureDetector = new GestureDetector(this);
		//
		mGestureDetector.setIsLongpressEnabled(false);
		getMAX_WIDTH();

	}

	void InitTitle() {
		titles.put(1, "-我要添加项目汇报");
		titles.put(2, "-我要添加项目周报");
		titles.put(3, "-我要添加项目发包");
		titles.put(4, "-我要添加项目提成");
		titles.put(5, "-我要添加外包申请");
		titles.put(6, "-我要添加项目协同");
	}

	class MyAdapter extends BaseAdapter {
		LayoutInflater mInflater;

		public MyAdapter(Context ctx) {
			mInflater = LayoutInflater.from(ctx);
		}

		@Override
		public int getCount() {
			return title.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MenuHold hold;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item, null);
				hold = new MenuHold();
				hold.tv = (TextView) convertView.findViewById(R.id.tv_item);
				convertView.setTag(hold);
			} else {
				hold = (MenuHold) convertView.getTag();
			}
			TextView tv = hold.tv;
			tv.setText(titles.get(position + 1));
			tv.setTag(position + 1);
			return convertView;
		}
	}

	class MenuHold {
		TextView tv;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);

		/***
		 * 卢伊林代码开始
		 * 
		 */

		imageButton_t1 = (Button) this.findViewById(R.id.imageButton_t1);
		imageButton_t1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				imageButton_t1.setBackgroundResource(R.drawable.prj1);
				Intent intent = new Intent(activity_menu.this,
						activity_prj_info_detail.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			}
		});

		imageButton_t2 = (Button) this.findViewById(R.id.imageButton_t2);
		imageButton_t2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imageButton_t2.setBackgroundResource(R.drawable.day1);
				Intent intent = new Intent(activity_menu.this,
						activity_prj_day.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			}
		});

		imageButton_t3 = (Button) this.findViewById(R.id.imageButton_t3);
		imageButton_t3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imageButton_t3.setBackgroundResource(R.drawable.week1);
				Intent intent = new Intent(activity_menu.this,
						activity_prj_week.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			}
		});

		imageButton_t4 = (Button) this.findViewById(R.id.imageButton_t4);
		imageButton_t4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imageButton_t4.setBackgroundResource(R.drawable.bonus1);
				Intent intent = new Intent(activity_menu.this,
						activity_prj_bonus.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			}
		});

		imageButton_t5 = (Button) this.findViewById(R.id.imageButton_t5);
		imageButton_t5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imageButton_t5.setBackgroundResource(R.drawable.out1);
				Intent intent = new Intent(activity_menu.this,
						activity_prj_out.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			}
		});

		imageButton_t6 = (Button) this.findViewById(R.id.imageButton_t6);
		imageButton_t6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imageButton_t6.setBackgroundResource(R.drawable.link1);
				Intent intent = new Intent(activity_menu.this,
						activity_prj_link.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			}
		});

		/***
		 * 卢伊林代码结束
		 * 
		 */

		InitView();
		InitTitle();
	}

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_menu);// 加载布局文件

	// imageButton_t1 = (Button) this.findViewById(R.id.imageButton_t1);
	// imageButton_t2 = (Button) this.findViewById(R.id.imageButton_t2);
	// imageButton_t3 = (Button) this.findViewById(R.id.imageButton_t3);
	// imageButton_t4 = (Button) this.findViewById(R.id.imageButton_t4);
	// imageButton_t5 = (Button) this.findViewById(R.id.imageButton_t5);
	// imageButton_t6 = (Button) this.findViewById(R.id.imageButton_t6);

	// imageButton_t1.setOnClickListener(this);
	// imageButton_t2.setOnClickListener(this);
	// imageButton_t3.setOnClickListener(this);
	// imageButton_t4.setOnClickListener(this);
	// imageButton_t5.setOnClickListener(this);
	// imageButton_t6.setOnClickListener(this);

	// imageButton_t1.setOnTouchListener(this);
	// imageButton_t1.setOnFocusChangeListener(this);
	// imageButton_t1.setOnKeyListener(this);

	/***
	 * listview代码
	 */
	void doScrolling(float distanceX) {
		isScrolling = true;
		mScrollX += distanceX;// distanceX:

		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
				.getLayoutParams();
		RelativeLayout.LayoutParams layoutParams_1 = (RelativeLayout.LayoutParams) layout_right
				.getLayoutParams();
		layoutParams.leftMargin -= mScrollX;
		layoutParams_1.leftMargin = window_width + layoutParams.leftMargin;
		if (layoutParams.leftMargin >= 0) {
			isScrolling = false;// AsynMove
			layoutParams.leftMargin = 0;
			layoutParams_1.leftMargin = window_width;

		} else if (layoutParams.leftMargin <= -MAX_WIDTH) {
			// AsynMove
			isScrolling = false;
			layoutParams.leftMargin = -MAX_WIDTH;
			layoutParams_1.leftMargin = window_width - MAX_WIDTH;
		}
		Log.v(TAG, "layoutParams.leftMargin=" + layoutParams.leftMargin
				+ ",layoutParams_1.leftMargin =" + layoutParams_1.leftMargin);

		layout_left.setLayoutParams(layoutParams);
		layout_right.setLayoutParams(layoutParams_1);
	}

	/***
	 * layout_left
	 */
	void getMAX_WIDTH() {
		ViewTreeObserver viewTreeObserver = layout_left.getViewTreeObserver();
		//
		viewTreeObserver.addOnPreDrawListener(new OnPreDrawListener() {
			@SuppressWarnings("deprecation")
			@Override
			public boolean onPreDraw() {
				if (!hasMeasured) {
					window_width = getWindowManager().getDefaultDisplay()
							.getWidth();
					MAX_WIDTH = layout_right.getWidth();
					RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
							.getLayoutParams();
					RelativeLayout.LayoutParams layoutParams_1 = (RelativeLayout.LayoutParams) layout_right
							.getLayoutParams();
					ViewGroup.LayoutParams layoutParams_2 = mylaout
							.getLayoutParams();
					// layout_left
					layoutParams.width = window_width;
					layout_left.setLayoutParams(layoutParams);

					// layout_right
					layoutParams_1.leftMargin = window_width;
					layout_right.setLayoutParams(layoutParams_1);
					// lv_set
					layoutParams_2.width = MAX_WIDTH;
					mylaout.setLayoutParams(layoutParams_2);

					Log.v(TAG, "MAX_WIDTH=" + MAX_WIDTH + "width="
							+ window_width);
					hasMeasured = true;
				}
				return true;
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
					.getLayoutParams();
			if (layoutParams.leftMargin < 0) {
				new AsynMove().execute(SPEED);
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		view = v;//

		//
		if (MotionEvent.ACTION_UP == event.getAction() && isScrolling == true) {
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
					.getLayoutParams();
			//
			if (layoutParams.leftMargin < -window_width / 2) {
				new AsynMove().execute(-SPEED);
			} else {
				new AsynMove().execute(SPEED);
			}
		}

		return mGestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {

		int position = lv_set.pointToPosition((int) e.getX(), (int) e.getY());
		if (position != ListView.INVALID_POSITION) {
			View child = lv_set.getChildAt(position
					- lv_set.getFirstVisiblePosition());
			if (child != null)
				child.setPressed(true);
		}

		mScrollX = 0;
		isScrolling = false;
		// true onSingleTapUp,
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	/***
	 *  
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// layout_left
		if (view != null && view == iv_set) {
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
					.getLayoutParams();
			//
			if (layoutParams.leftMargin >= 0) {
				new AsynMove().execute(-SPEED);
				lv_set.setSelection(0); //
			} else {
				//
				new AsynMove().execute(SPEED);
			}
		} else if (view != null && view == layout_left) {
			RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) layout_left
					.getLayoutParams();
			if (layoutParams.leftMargin < 0) {
				// layout_left layout_left .( )
				//
				new AsynMove().execute(SPEED);
			}
		}

		return true;
	}

	/***
	 * distanceX=
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// ִ�л���.
		doScrolling(distanceX);
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	class AsynMove extends AsyncTask<Integer, Integer, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			int times = 0;
			if (MAX_WIDTH % Math.abs(params[0]) == 0)// ���
				times = MAX_WIDTH / Math.abs(params[0]);
			else
				times = MAX_WIDTH / Math.abs(params[0]) + 1;// ������

			for (int i = 0; i < times; i++) {
				publishProgress(params[0]);
				try {
					Thread.sleep(sleep_time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			return null;
		}

		/**
		 * update UI
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
					.getLayoutParams();
			RelativeLayout.LayoutParams layoutParams_1 = (RelativeLayout.LayoutParams) layout_right
					.getLayoutParams();
			// ���ƶ�
			if (values[0] > 0) {
				layoutParams.leftMargin = Math.min(layoutParams.leftMargin
						+ values[0], 0);
				layoutParams_1.leftMargin = Math.min(layoutParams_1.leftMargin
						+ values[0], window_width);
				Log.v(TAG, "layout_left" + layoutParams.leftMargin
						+ ",layout_right" + layoutParams_1.leftMargin);
			} else {
				// ���ƶ�
				layoutParams.leftMargin = Math.max(layoutParams.leftMargin
						+ values[0], -MAX_WIDTH);
				layoutParams_1.leftMargin = Math.max(layoutParams_1.leftMargin
						+ values[0], window_width - MAX_WIDTH);
				Log.v(TAG, "layout_left" + layoutParams.leftMargin
						+ ",layout_right" + layoutParams_1.leftMargin);
			}
			layout_right.setLayoutParams(layoutParams_1);
			layout_left.setLayoutParams(layoutParams);

		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
				.getLayoutParams();
		//
		TextView tv = (TextView) view.findViewById(R.id.tv_item);
		int key = (Integer) tv.getTag();
		if (layoutParams.leftMargin == -MAX_WIDTH) {
			switch (key) {
			case 1:
				Intent activity_prj_day = new Intent(activity_menu.this,
						activity_prj_day.class);
				startActivity(activity_prj_day);
				break;
			case 2:
				Intent activity_prj_week = new Intent(activity_menu.this,
						activity_prj_week.class);
				startActivity(activity_prj_week);
				break;
			case 3:
				// Intent activity_prj_day = new Intent(activity_menu.this,
				// activity_prj_day.class);
				// startActivity(intent);
				break;
			case 4:
				Intent activity_prj_bonus = new Intent(activity_menu.this,
						activity_prj_bonus.class);
				startActivity(activity_prj_bonus);
				break;
			case 5:
				Intent activity_prj_out = new Intent(activity_menu.this,
						activity_prj_out.class);
				startActivity(activity_prj_out);
				break;
			case 6:
				Intent activity_prj_link = new Intent(activity_menu.this,
						activity_prj_link.class);
				startActivity(activity_prj_link);
				break;
			default:
				break;
			}
		}
	}

}