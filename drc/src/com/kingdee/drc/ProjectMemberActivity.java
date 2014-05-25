package com.kingdee.drc;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kingdee.drc.entity.ProjectMember;

public class ProjectMemberActivity extends Activity {
	private ListView lvProjectMember;
	private List<ProjectMember> pms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_member);
		lvProjectMember = (ListView) findViewById(R.id.lvProjectMember);
		Intent intent = getIntent();
		String dataStr = intent.getStringExtra("projectMember");
		try {
			JSONArray datas = new JSONArray(dataStr);
			pms = new ArrayList<ProjectMember>();
			for (int i = 0; i < datas.length(); i++) {
				JSONObject data = datas.getJSONObject(i);
				ProjectMember pm = new ProjectMember();
				pm.setName(data.getString("成员姓名"));
				pm.setRole(data.getString("项目角色"));
				pm.setMobile(data.getString("联系电话"));
				pms.add(pm);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		MyAdapter adapter = new MyAdapter();
		lvProjectMember.setAdapter(adapter);
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return pms.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
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
						R.layout.activity_project_member_item, null);
				holder = new ViewHolder();
				holder.valName = (TextView) convertView
						.findViewById(R.id.valName);
				holder.valRole = (TextView) convertView
						.findViewById(R.id.valRole);
				holder.valMobile = (TextView) convertView
						.findViewById(R.id.valMobile);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			ProjectMember pMember = pms.get(position);
			holder.valName.setText(pMember.getName());
			holder.valRole.setText(pMember.getRole());
			holder.valMobile.setText(pMember.getMobile());
			
			return convertView;
		}

	}

	class ViewHolder {
		TextView valName;
		TextView valRole;
		TextView valMobile;
	}
}
