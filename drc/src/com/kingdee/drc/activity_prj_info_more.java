package com.kingdee.drc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.kingdee.drc.corner.MyListView;
import com.kingdee.drc.model.ProjectModel;

@SuppressLint("ShowToast")
public class activity_prj_info_more extends Activity implements
		OnItemClickListener {
	private MyListView listView_2; // listView_1, , listView_3
	private ArrayList<Map<String, String>> listData2; // listData, listData2,
														// listData3
	private SimpleAdapter adapter;
	private TextView projectName;
	private TextView projectAmount;
	private TextView planDays;
	private String projectNo;
	private boolean isDataLoaded = true;
	private JSONObject data;
	private int selectedId = -1;
	ProgressDialog pdDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("activity_prj_info_more");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_prj_info_more);// 加载布局文件
		projectName = (TextView) findViewById(R.id.projectName);
		projectAmount = (TextView) findViewById(R.id.projectAmount);
		planDays = (TextView) findViewById(R.id.planDays);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		projectName.setText(bundle.getString("projectName"));
		projectAmount.setText(bundle.getString("impAmount"));
		planDays.setText(bundle.getString("conDays"));
		this.projectNo = bundle.getString("projectName");
		// listView_1 = (MyListView) findViewById(R.id.mylistview_1);
		listView_2 = (MyListView) findViewById(R.id.mylistview_2);
		// listView_3 = (MyListView) findViewById(R.id.mylistview_3);

		// listView_1.setAdapter(getSimpleAdapter_1());
		listView_2.setAdapter(getSimpleAdapter_2());
		// listView_3.setAdapter(getSimpleAdapter_3());

		// listView_1.setOnItemClickListener(this);
		listView_2.setOnItemClickListener(this);
		// listView_3.setOnItemClickListener(this);

		// setListViewHeightBasedOnChildren(listView_1);
		setListViewHeightBasedOnChildren(listView_2);
		// setListViewHeightBasedOnChildren(listView_3);
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				ProjectModel pmModel = new ProjectModel();
				Map<String, String> paramses = new HashMap<String, String>();
				try {
					data = pmModel.getProjectDetail(paramses);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(Void result) {
				isDataLoaded = true;
				if (selectedId != -1) {
					switch (selectedId) {
					case 0:
						if (pdDialog != null && pdDialog.isShowing()) {
							pdDialog.cancel();
						}
						try {
							if (!data.isNull("projectInfo")
									&& data.getJSONObject("projectInfo")
											.length() > 0) {
								Intent intent = new Intent(
										activity_prj_info_more.this,
										ProjectBaseInfoActivity.class);
								intent.putExtra("projectInfo", data
										.getJSONObject("projectInfo")
										.toString());
								startActivity(intent);
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
				}
			};
		}.execute();
	}

	/**
	 * listView_1
	 */
	// private SimpleAdapter getSimpleAdapter_1() {
	// listData = new ArrayList<Map<String, String>>();
	//
	// Map<String, String> map = new HashMap<String, String>();
	// map.put("text", "项目");
	// listData.add(map);
	//
	// map = new HashMap<String, String>();
	// map.put("text", "项目信息");
	// listData.add(map);
	//
	// return new SimpleAdapter(activity_prj_index.this, listData,
	// R.layout.list_item, new String[] { "text" },
	// new int[] { R.id.tv_list_item });
	//
	// }

	/**
	 * listView_2
	 */
	private SimpleAdapter getSimpleAdapter_2() {
		listData2 = new ArrayList<Map<String, String>>();

		Map<String, String> map = new HashMap<String, String>();
		map.put("text", "项目基本信息");
		listData2.add(map);

		map = new HashMap<String, String>();
		map.put("text", "项目成员信息");
		listData2.add(map);

		map = new HashMap<String, String>();
		map.put("text", "项目奖金预算信息");
		listData2.add(map);

		map = new HashMap<String, String>();
		map.put("text", "项目金额及人天信息");
		listData2.add(map);

		map = new HashMap<String, String>();
		map.put("text", "项目任务包信息");
		listData2.add(map);

		return new SimpleAdapter(activity_prj_info_more.this, listData2,
				R.layout.list_item, new String[] { "text" },
				new int[] { R.id.tv_list_item });

	}

	/**
	 * listView_3
	 */
	// private SimpleAdapter getSimpleAdapter_3() {
	// listData3 = new ArrayList<Map<String, String>>();
	//
	// Map<String, String> map = new HashMap<String, String>();
	// map.put("text", "项目基本信息");
	// listData3.add(map);
	//
	// map = new HashMap<String, String>();
	// map.put("text", "项目成员信息");
	// listData3.add(map);
	//
	// map = new HashMap<String, String>();
	// map.put("text", "项目奖金预算信息");
	// listData3.add(map);
	//
	// map = new HashMap<String, String>();
	// map.put("text", "项目任务包及人天信息");
	// listData3.add(map);
	//
	// return new SimpleAdapter(activity_prj_index.this, listData3,
	// R.layout.list_item, new String[] { "text" },
	// new int[] { R.id.tv_list_item });
	// }

	/***
	 * listview
	 * 
	 * @param listView
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// params.height += 5;// if without this statement,the listview will be
		// a
		// little short
		// listView.getDividerHeight()��ȡ�����ָ���ռ�õĸ߶�
		// params.height���õ����ListView������ʾ��Ҫ�ĸ߶�
		listView.setLayoutParams(params);
	}

	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	//
	// if (parent == listView_1) {
	// Map<String, String> map = listData.get(position);
	// Toast.makeText(activity_prj_index.this, map.get("text"), 1).show();
	// } else if (parent == listView_2) {
	// Map<String, String> map = listData2.get(position);
	// Toast.makeText(activity_prj_index.this, map.get("text"), 1).show();
	// } else if (parent == listView_3) {
	// Map<String, String> map = listData3.get(position);
	// Toast.makeText(activity_prj_index.this, map.get("text"), 1).show();
	// }
	//
	// }

	// 改造只剩下listView_2

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Map<String, String> map = listData2.get(position);
		Toast.makeText(activity_prj_info_more.this, map.get("text"), 1).show();
		if (isDataLoaded) {
			Intent intent = new Intent(activity_prj_info_more.this,
					ProjectBaseInfoActivity.class);
			startActivity(intent);
		} else {
			pdDialog = new ProgressDialog(activity_prj_info_more.this);
			pdDialog.show(activity_prj_info_more.this, "提示", "正在加载数据....",
					true, true);
			selectedId = 0;
		}
	}

}