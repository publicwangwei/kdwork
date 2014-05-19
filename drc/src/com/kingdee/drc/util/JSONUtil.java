package com.kingdee.drc.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用于解析返回的JSON数据
 * 
 * @author ryanwork
 * 
 */
public class JSONUtil {

	private String code;
	private String msg;
	// private String token;
	private JSONArray data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	// public String getToken() {
	// return token;
	// }
	//
	// public void setToken(String token) {
	// this.token = token;
	// }

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}

	public void parseJsonStr(String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		JSONObject head = obj.getJSONObject("head");
		this.code = head.getString("code");
		this.msg = head.getString("msg");
		if (!head.isNull("token")) {
			Constant.TOKEN = head.getString("token");
		}
		// 检查data节点是否存在
		if (!obj.isNull("data")) {
			this.data = obj.getJSONArray("data");
		}
	}

	@Override
	public String toString() {
		JSONObject res = new JSONObject();
		JSONObject head = new JSONObject();
		try {
			head.put("code", this.code);
			head.put("msg", this.msg);
			// head.put("token", this.token);
			res.put("head", head);
			res.put("data", this.data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return res.toString();
	}
}
