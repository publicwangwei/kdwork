package com.kingdee.drc;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kingdee.drc.util.Constant;
import com.kingdee.drc.util.Des3Util;
import com.kingdee.drc.util.HttpUtil;




public class LoginActivity extends Activity {

	private Button button;
	private EditText etUser;
	private EditText etPassword;

	// private Button button1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// requestWindowFeature(Window.FEATURE_NO_TITLE); // 无title
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN); // 在Java中实现全屏，缺点是要闪一下

		setContentView(R.layout.activity_login);// 加载布局文件

		etUser = (EditText) findViewById(R.id.etUser);
		etPassword = (EditText) findViewById(R.id.etPassword);
		SharedPreferences sp = getSharedPreferences("Account", MODE_PRIVATE);
		etUser.setText(sp.getString("user", ""));

		// 以下代码为卢伊林在2014年2月28日希望通过Intent传递数据加载activity_menu页；

		button = (Button) this.findViewById(R.id.button);
		// button1 = (Button) this.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String user = etUser.getText().toString();
				String pwd = etPassword.getText().toString();
				SharedPreferences sp = getSharedPreferences("Account",
						MODE_PRIVATE);
				Editor ed = sp.edit();
				ed.putString("user", user);
				ed.commit();
				new AsyncTask<String, Void, String>() {

					@Override
					protected String doInBackground(String... params) {
						Map<String, String> reparams = new HashMap<String, String>();
						reparams.put("user", params[0]);
						reparams.put("password", params[1]);
						String result = HttpUtil.postData(Constant.APP_LOGIN,
								reparams);
						return result;
					}

					@Override
					protected void onPostExecute(String result) {
						
						String code = "";
						String msg = "";
						try {
							if (result != null) {
								JSONObject obj = new JSONObject(result);
								code = obj.getString("code");
								msg = obj.getString("msg");
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Toast.makeText(LoginActivity.this, msg,
								Toast.LENGTH_SHORT).show();
						if ("0".equals(code)) {
							Intent intent = new Intent(LoginActivity.this,
									activity_menu.class);
							startActivity(intent);
							

						}
					}
				}.execute(new String[] { user, Des3Util.encrypt(pwd) });

			}
		});

		// button1.setOnClickListener( new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent intent = new Intent(LoginActivity.this,
		// activity_prj_info_detail.class);
		// startActivity(intent);
		// }
		// });

	}
}