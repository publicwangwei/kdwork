package com.kingdee.drc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_prj_index extends Activity {

	private Button button_find;// 进入详细项目页面的按钮
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prj_index);// 加载布局文件

	
		button_find = (Button) this.findViewById(R.id.button_find);
		button_find.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//button_find.setBackgroundResource(R.drawable.prj1);
				Intent intent = new Intent(activity_prj_index.this,
						activity_prj_info_detail.class);
				startActivity(intent);
			}
		});
	
	
	}

}
