package com.kingdee.drc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.kingdee.drc.entity.ProjectInfo;
import com.kingdee.drc.util.Constant;
import com.kingdee.drc.util.HttpUtil;
import com.kingdee.drc.util.JSONUtil;

public class ProjectModel {
	private Context ctx;

	public ProjectModel() {
	}

	public ProjectModel(Context ctx) {
		this.ctx = ctx;
	}

	public List<ProjectInfo> getProjectList(Map<String, String> params)
			throws Exception {
		List<ProjectInfo> projectInfos = null;
		String result = HttpUtil.postData(Constant.GET_PROJECTS_METHOD, params);
		JSONObject obj = new JSONObject(result);
		JSONUtil jsonUtil = new JSONUtil();
		jsonUtil.parseJsonStr(result);
		if ("0".equals(jsonUtil.getCode())) {
			if (jsonUtil.getData() != null) {
				projectInfos = new ArrayList<ProjectInfo>();
				ProjectInfo proInfo;
				JSONObject data;
				JSONArray datas = jsonUtil.getData();
				for (int i = 0; i < datas.length(); i++) {
					data = datas.optJSONObject(i);
					proInfo = new ProjectInfo();
					proInfo.setProjectNo(data.getString("projectNo"));
					proInfo.setProjectName(data.getString("projectName"));
					proInfo.setConDays(data.getString("conDays"));
					proInfo.setImpAmount(data.getString("impAmount"));
					projectInfos.add(proInfo);
				}
			}
		} else {
			throw new Exception(jsonUtil.getMsg());
		}

		return projectInfos;
	}

	public JSONObject getProjectDetail(Map<String, String> params)
			throws Exception {
		List<ProjectInfo> projectInfos = null;
		String result = HttpUtil.postData(Constant.GET_PROJECTDETAIL_METHOD, params);
		JSONObject obj = new JSONObject(result);
		JSONUtil jsonUtil = new JSONUtil();
		jsonUtil.parseJsonStr(result);
		if ("0".equals(jsonUtil.getCode())) {
			if (jsonUtil.getData() != null) {
				projectInfos = new ArrayList<ProjectInfo>();
				ProjectInfo proInfo;
				JSONArray datas = jsonUtil.getData();
				return datas.length() > 0 ? datas.optJSONObject(0) : null;
			}
		} else {
			throw new Exception(jsonUtil.getMsg());
		}
		return null;
	}
}
