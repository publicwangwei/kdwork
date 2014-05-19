package com.kingdee.drc.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.kingdee.drc.util.Constant;
import com.kingdee.drc.util.HttpUtil;
import com.kingdee.drc.util.JSONUtil;

public class LoginModel {
	private Context ctx;

	public LoginModel(Context ctx) {
		this.ctx = ctx;
	}

	public String[] login(Map<String, String> params) {
		String[] res = new String[2];
		try {
			String result = HttpUtil.postData(Constant.LOGIN_METHOD, params);
			JSONObject obj = new JSONObject(result);
			JSONUtil jsonUtil = new JSONUtil();
			jsonUtil.parseJsonStr(result);
			res[0] = jsonUtil.getCode();
			res[1] = jsonUtil.getMsg();
		} catch (JSONException e) {
			e.printStackTrace();
			res[1] = this.ctx.getResources().getString(
					com.kingdee.drc.R.string.net_disconect);
			if (Constant.DEBUG) {
				res[1] = e.getMessage();
			}
		}
		return res;
	}
}
