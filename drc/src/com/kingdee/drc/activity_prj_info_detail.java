package com.kingdee.drc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.anim;
import android.R.color;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.TextView;
import android.widget.Toast;

import com.kingdee.drc.entity.ProjectInfo;
import com.kingdee.drc.model.ProjectModel;
import com.kingdee.drc.util.Constant;

public class activity_prj_info_detail extends Activity {

	private Button imageButton_t;// 进入详细项目页面的按钮
	private Button imageButton_t0;// 进入详细项目页面的按钮
	private Button imageButton_t3;// 进入详细项目页面的按钮
	private Button imageButton_t5;// 进入详细项目页面的按钮

	private ListView listView1;
	private ArrayAdapter<String> adapter1;
	private MyAdapter adpater;
	private SearchView sv;

	// 表示数据源
	// private List<String> data1 = null;
	private List<String> prData = null;
	private EditText searchText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setTitle("详细项目信息>>");// 在Manifest设置全屏后将不再显示
		setContentView(R.layout.activity_prj_info_detail);// 加载布局文件
		doMySearch(null);
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
				// Intent intent = new Intent(activity_prj_info_detail.this,
				// activity_prj_index.class);
				// startActivity(intent);
				if (sv.getVisibility() == View.GONE) {
					sv.setVisibility(View.VISIBLE);
					// 设置是否展开
					// sv.setIconifiedByDefault(false);
					sv.setIconified(false);
					Animation am = AnimationUtils.loadAnimation(
							activity_prj_info_detail.this, anim.slide_in_left);
					sv.startAnimation(am);
				}
			}
		});
		// HOME按钮
		imageButton_t3 = (Button) this.findViewById(R.id.imageButton_t3);
		imageButton_t3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
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

		// data1 = activity_prj_datesourse.getDataSource();// 赋值给Data
		// adapter1 = new ArrayAdapter<String>(activity_prj_info_detail.this,
		// R.layout.simple_list_item, data1);
		//
		sv = (SearchView) findViewById(R.id.searchView);
		SearchableInfo searchable = ((SearchManager) getSystemService(Context.SEARCH_SERVICE))
				.getSearchableInfo(getComponentName());
		sv.setSearchableInfo(searchable);
		sv.setOnCloseListener(new OnCloseListener() {
			@Override
			public boolean onClose() {
				// Animation am = AnimationUtils.loadAnimation(
				// activity_prj_info_detail.this, anim.slide_out_right);
				// sv.startAnimation(am);
				sv.setVisibility(View.GONE);
				return false;
			}
		});
		listView1 = (ListView) this.findViewById(R.id.listview1);
		adpater = new MyAdapter();
		listView1.setAdapter(adpater);
		listView1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String prjNo = (String) view.findViewById(R.id.prjName)
						.getTag();
				Toast.makeText(activity_prj_info_detail.this, prjNo,
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(activity_prj_info_detail.this,
						activity_prj_info_more.class);
				ProjectInfo pInfo = adpater.getDatasList().get(position);
				Bundle extras = new Bundle();
				extras.putString("projectNo", pInfo.getProjectNo());
				extras.putString("projectName", pInfo.getProjectName());
				extras.putString("impAmount", pInfo.getImpAmount());
				extras.putString("conDays", pInfo.getConDays());
				intent.putExtras(extras);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			doMySearch(query);
		}
	}

	private void doMySearch(final String query) {
		new AsyncTask<Void, Integer, String>() {
			List<ProjectInfo> datas;

			@Override
			protected String doInBackground(Void... paramses) {
				ProjectModel pModel = new ProjectModel();
				Map<String, String> params = new HashMap<String, String>();
				params.put("user", Constant.USER);
				params.put("projectName", query);
				try {
					datas = pModel.getProjectList(params);
				} catch (Exception e) {
					e.printStackTrace();
					return e.getMessage();
				}
				return null;
			}

			protected void onPostExecute(String result) {
				if (result != null && Constant.DEBUG) {
					Toast.makeText(activity_prj_info_detail.this, result,
							Toast.LENGTH_SHORT).show();
				}
				if (datas == null) {
					Toast.makeText(activity_prj_info_detail.this,
							getString(R.string.prj_noDatas), Toast.LENGTH_SHORT)
							.show();
				}
				adpater.setDatasList(datas);
				adpater.notifyDataSetChanged();
			};
		}.execute();
	}

	class MyAdapter extends BaseAdapter {
		private List<ProjectInfo> datasList;

		public void setDatasList(List<ProjectInfo> datasList) {
			this.datasList = datasList;
		}

		public List<ProjectInfo> getDatasList() {
			return datasList;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if (convertView == null) {
				holder = new Holder();
				convertView = LayoutInflater.from(getBaseContext()).inflate(
						R.layout.prj_info_list, null);
				holder.prjName = (TextView) convertView
						.findViewById(R.id.prjName);
				holder.prjInfo = (TextView) convertView
						.findViewById(R.id.prjInfo);
				// holder.prjNo = (TextView) convertView
				// .findViewById(R.id.prjNo);
				convertView.setTag(holder);
			}
			if (datasList != null) {
				ProjectInfo prjInfo = datasList.get(position);
				holder = (Holder) convertView.getTag();
				holder.prjName.setText(prjInfo.getProjectName());
				Spanned ss = Html.fromHtml(getString(R.string.prj_impAmount)
						+ ":" + prjInfo.getImpAmount() + "\t"
						+ getString(R.string.prj_conDays) + ":"
						+ prjInfo.getConDays());
				holder.prjName.setTag(prjInfo.getProjectNo());
				holder.prjInfo.setText(ss);
				// holder.prjNo.setText(prjInfo.getProjectNo());
			}
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
			return datasList == null ? 0 : datasList.size();
		}
	}

	class Holder {
		TextView prjName;
		TextView prjInfo;
		TextView prjNo;
	}
}
