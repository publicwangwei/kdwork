package com.kingdee.drc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kingdee.drc.R.layout;
import com.kingdee.drc.util.Constant;

public class ProjectBaseInfoActivity extends Activity {
	private ListView prjBaseInfo;
	private List<RowData> baseInfo;
	private List<RowData> projectDetail;
	private Handler handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_baseinfo);
		initData();
		prjBaseInfo = (ListView) findViewById(R.id.prjBaseInfo);
		MyAdapter adapter = new MyAdapter();
		prjBaseInfo.setAdapter(adapter);
		handler=new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				
			}
		};
	}

	private void initData() {
		try {
			baseInfo = new ArrayList<ProjectBaseInfoActivity.RowData>();
			projectDetail = new ArrayList<ProjectBaseInfoActivity.RowData>();
			Intent intent = getIntent();
			String dataStr = intent.getStringExtra("projectInfo");
			JSONObject projectData = new JSONObject(dataStr);
			JSONObject baseObject = projectData.getJSONObject("baseInfo");
			JSONObject projectObject = projectData
					.getJSONObject("projectDetail");
			Iterator<String> dataIterator = baseObject.keys();
			while (dataIterator.hasNext()) {
				String name = dataIterator.next();
				RowData rowData = new RowData();
				rowData.title = name;
				rowData.value = baseObject.getString(name);
				baseInfo.add(rowData);
			}
			dataIterator = projectObject.keys();
			while (dataIterator.hasNext()) {
				String name = dataIterator.next();
				RowData rowData = new RowData();
				rowData.title = name;
				rowData.value = projectObject.getString(name);
				projectDetail.add(rowData);
			}
		} catch (JSONException e) {
			if (Constant.DEBUG) {
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return baseInfo.size() + projectDetail.size() + 2;
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
			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(getBaseContext()).inflate(
						layout.activity_project_baseinfoitem, null);
				holder = new ViewHolder();
				holder.attribute = (TextView) convertView
						.findViewById(R.id.attribute);
				holder.value = (TextView) convertView.findViewById(R.id.value);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			if (position == 0) {
				holder.attribute.setText("基本信息");
				holder.attribute.setTextColor(Color.BLACK);
				holder.attribute.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
				holder.value.setVisibility(View.GONE);
				convertView.findViewById(R.id.secend_line).setVisibility(
						View.GONE);
			} else if (position < baseInfo.size() + 1) {
				holder.attribute.setText(baseInfo.get(position - 1).title);
				holder.value.setText(baseInfo.get(position - 1).value);
				holder.attribute.setTextColor(Color.parseColor("#808080"));
				holder.attribute.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				holder.value.setVisibility(View.VISIBLE);
				convertView.findViewById(R.id.secend_line).setVisibility(
						View.VISIBLE);
			} else if (position == baseInfo.size() + 1) {
				holder.attribute.setText("项目信息");
				holder.attribute.setTextColor(Color.BLACK);
				holder.attribute.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
				holder.value.setVisibility(View.GONE);
				convertView.findViewById(R.id.secend_line).setVisibility(
						View.GONE);
			} else {
				holder.attribute.setText(projectDetail.get(position
						- baseInfo.size() - 2).title);
				holder.value.setText(projectDetail.get(position
						- baseInfo.size() - 2).value);
				holder.attribute.setTextColor(Color.parseColor("#808080"));
				holder.attribute.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				holder.value.setVisibility(View.VISIBLE);
				convertView.findViewById(R.id.secend_line).setVisibility(
						View.VISIBLE);
			}
			return convertView;
		}
	}

	class ViewHolder {
		TextView attribute;
		TextView value;
	}

	class RowData {
		String title;
		String value;
	}
}
