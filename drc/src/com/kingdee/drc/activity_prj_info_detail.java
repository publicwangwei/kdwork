package com.kingdee.drc;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class activity_prj_info_detail extends Activity {

	private Button imageButton_t;// 进入详细项目页面的按钮
	private Button imageButton_t0;// 进入详细项目页面的按钮
	private Button imageButton_t3;// 进入详细项目页面的按钮
	private Button imageButton_t5;// 进入详细项目页面的按钮

	private ListView listView1;
	private ArrayAdapter<String> adapter1;

	// 表示数据源
	private List<String> data1 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setTitle("详细项目信息>>");// 在Manifest设置全屏后将不再显示
		setContentView(R.layout.activity_prj_info_detail);// 加载布局文件

		// 返回按钮
		imageButton_t = (Button) this.findViewById(R.id.imageButton_t);
		imageButton_t.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity_prj_info_detail.this,
						activity_prj_index.class);
				startActivity(intent);
			}
		});
		// 查找按钮
		imageButton_t0 = (Button) this.findViewById(R.id.imageButton_t0);
		imageButton_t0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity_prj_info_detail.this,
						activity_prj_index.class);
				startActivity(intent);
			}
		});
		// HOME按钮
		imageButton_t3 = (Button) this.findViewById(R.id.imageButton_t3);
		imageButton_t3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity_prj_info_detail.this,
						activity_menu.class);
				startActivity(intent);
			}
		});

		// 详细按钮
		imageButton_t5 = (Button) this.findViewById(R.id.imageButton_t5);
		imageButton_t5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity_prj_info_detail.this,
						activity_prj_info_more.class);
				startActivity(intent);
			}
		});

		data1 = activity_prj_datesourse.getDataSource();// 赋值给Data
		// adapter1 = new ArrayAdapter<String>(activity_prj_info_detail.this,
		// R.layout.simple_list_item, data1);
		//

		listView1 = (ListView) this.findViewById(R.id.listview1);
		listView1.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Holder holder;
				if (convertView == null) {
					holder = new Holder();
					convertView = LayoutInflater.from(getBaseContext())
							.inflate(R.layout.prj_info_list, null);
					holder.prjName = (TextView) convertView
							.findViewById(R.id.prjName);
					holder.prjInfo = (TextView) convertView
							.findViewById(R.id.prjInfo);
					convertView.setTag(holder);
				}
				holder = (Holder) convertView.getTag();
				holder.prjName.setText(data1.get(position));
				holder.prjInfo.setText("项目金额：100w 项目人天：100d");
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public int getCount() {
				return data1.size();
			}
		});

	}

	class Holder {
		TextView prjName;
		TextView prjInfo;
	}
}
