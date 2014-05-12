package com.kingdee.drc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_prj_day extends Activity {
	
	
	
	private Button imageButton_t5;//进入详细项目页面的按钮
	
	

	public activity_prj_day() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		

		setTitle("activity_prj_day");
		setContentView(R.layout.activity_prj_day);// 加载布局文件
		
		
		imageButton_t5 = (Button) this.findViewById(R.id.imageButton_t5);
		imageButton_t5.setOnClickListener(  new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity_prj_day.this,
						activity_prj_info_more.class);
				startActivity(intent);
			}
	                        	});
	
		
		
		
		
	}
}
