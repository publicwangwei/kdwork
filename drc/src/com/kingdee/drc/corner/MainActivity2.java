package com.kingdee.drc.corner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kingdee.drc.R;
import com.kingdee.drc.activity_prj_info_more;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends Activity {
	private LinearLayout ll_main;
	private TableLayout tableLayout;

	private LinearLayout.LayoutParams layoutParams;

	private static final String MSG_0[] = { "项目信息" };

	private static final String MSG_1[] = { "项目基本信息", "项目基本信息2" };

	private static final String MSG_2[] = { "项目成员信息", "项目成员信息1", "项目成员信息2", "项目成员信息3",
			"项目成员信息4" };

	private static final String MSG_3[] = { "项目奖金预算信息1", "项目奖金预算信息2", "项目奖金预算信息3", "项目奖金预算信息4" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main2);
		ll_main = (LinearLayout) findViewById(R.id.ll_main);
		showTable();
	}

	/***
	 * table
	 */
	public void showTable() {

		layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		layoutParams.bottomMargin = 30;
		layoutParams.topMargin = 10;
		ll_main.addView(getTable(MSG_0), layoutParams);

		ll_main.addView(getTable(MSG_1), layoutParams);

		ll_main.addView(getTable(MSG_2), layoutParams);

		ll_main.addView(getTable(MSG_3), layoutParams);
	}

	/***
	 *  Table
	 * 
	 * @param array
	 * @return
	 */
	int i;

	public TableLayout getTable(final String[] array) {

		tableLayout = new TableLayout(this);
		tableLayout.setLayoutParams(layoutParams);
		tableLayout.setStretchAllColumns(true);

		for (i = 0; i < array.length; i++) {
			TableRow tableRow = new TableRow(this);
			View view = getView(array[i], i, array.length);
			tableRow.addView(view);

			tableLayout.addView(tableRow);

		}
		return tableLayout;

	}

	/****
	 * 
	 * @param msg
	 *           
	 * @param current_Id
	 *            
	 * @param totle_Num
	 *            
	 * @return
	 */
	public View getView(final String msg, int current_Id, int totle_Num) {

		LinearLayout linearLayout = new LinearLayout(this);

		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layoutParams1.height = 1;
		linearLayout.setOrientation(1);
		//  
		View line = new View(this);
		line.setLayoutParams(layoutParams1);
		line.setBackgroundColor(getResources().getColor(R.color.black));

		View view = LayoutInflater.from(MainActivity2.this).inflate(
				R.layout.item, null);
		view.setBackgroundDrawable(new BitmapDrawable());

		view.setFocusable(true);
		view.setClickable(true);
		TextView textView = (TextView) view.findViewById(R.id.tv_list_item);
		textView.setText(msg);
		textView.setTextSize(20);

		//  Table view 
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (msg.equals(MSG_0[0])) {
					startActivity(new Intent(MainActivity2.this,
							activity_prj_info_more.class));
				} else {
					Toast.makeText(MainActivity2.this, msg, 1).show();
				}

			}
		});

		// 
		if (totle_Num == 1) {
			view.setBackgroundResource(R.drawable.default_selector);
			return view;
		}
		//  
		else if (current_Id == 0) {
			view.setBackgroundResource(R.drawable.list_top_selector);
		}
		//  
		else if (current_Id == totle_Num - 1) {
			view.setBackgroundResource(R.drawable.list_bottom_selector);
			line.setVisibility(View.GONE);
		} else
			view.setBackgroundResource(R.drawable.list_center_selector);

		linearLayout.addView(view);
		linearLayout.addView(line);

		return linearLayout;
	}

}
