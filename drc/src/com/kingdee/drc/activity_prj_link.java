	package com.kingdee.drc;

	import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

	public class activity_prj_link extends Activity {

		public activity_prj_link() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			setTitle("activity_prj_link");
			setContentView(R.layout.activity_prj_link);// 加载布局文件
			
			
//
//	 		TextView textView = (TextView) this.findViewById(R.id.textview);
//			TextView textView2 = (TextView) this.findViewById(R.id.textview2);
//			TextView textView3 = (TextView) this.findViewById(R.id.textview3);
//			TextView textView4 = (TextView) this.findViewById(R.id.textview4);
//			TextView textView5 = (TextView) this.findViewById(R.id.textview5);
			TextView textView6 = (TextView) this.findViewById(R.id.textview6);

			
//	 		String text1 = "项目信息";
//			String text2 = "项目汇报";
//	 		String text3 = "项目周报";
//			String text4 = "提成管理";
//	 		String text5 = "外包管理";
			String text6 = "项目协同";
			

			
			// 主要是用来拆分字符串
//	        SpannableString spannableString = new SpannableString(text1);
//			SpannableString spannableString2 = new SpannableString(text2);
//			SpannableString spannableString3 = new SpannableString(text3);
//			SpannableString spannableString4 = new SpannableString(text4);
//			SpannableString spannableString5 = new SpannableString(text5);
			SpannableString spannableString6 = new SpannableString(text6);

//
//			//跳转
//			spannableString.setSpan(new ClickableSpan() {
//
//				@Override
//				public void onClick(View widget) {
//					// TODO Auto-generated method stub
//					Intent intent = new Intent(activity_prj_link.this,
//							activity_prj_info.class);
//					startActivity(intent);
//				}
//			}, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//			
//			//跳转
//			
//			spannableString2.setSpan(new ClickableSpan() {
//
//				@Override
//				public void onClick(View widget) {
//					// TODO Auto-generated method stub
//					Intent intent = new Intent(activity_prj_link.this,
//							activity_prj_day.class);
//					startActivity(intent);
//				}
//			}, 0, text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//			
//			//跳转
//			
//			spannableString3.setSpan(new ClickableSpan() {
//
//				@Override
//				public void onClick(View widget) {
//					// TODO Auto-generated method stub
//					Intent intent = new Intent(activity_prj_link.this,
//							activity_prj_week.class);
//					startActivity(intent);
//				}
//			}, 0, text3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//			//跳转
//			
//			spannableString4.setSpan(new ClickableSpan() {
//
//				@Override
//				public void onClick(View widget) {
//					// TODO Auto-generated method stub
//					Intent intent = new Intent(activity_prj_link.this,
//							activity_prj_bonus.class);
//					startActivity(intent);
//				}
//			}, 0, text4.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//			
//			//跳转
//			
//			spannableString5.setSpan(new ClickableSpan() {
//
//				@Override
//				public void onClick(View widget) {
//					// TODO Auto-generated method stub
//					Intent intent = new Intent(activity_prj_link.this,
//							activity_prj_out.class);
//					startActivity(intent);
//				}
//			}, 0, text5.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			//跳转
			
			spannableString6.setSpan(new ClickableSpan() {

				@Override
				public void onClick(View widget) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(activity_prj_link.this,
							activity_menu.class);
					startActivity(intent);
				}
			}, 0, text6.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


//			textView.setText(spannableString);
//			textView2.setText(spannableString2);
//			textView3.setText(spannableString3);
//			textView4.setText(spannableString4);
//			textView5.setText(spannableString5);
			textView6.setText(spannableString6);
			
//			
//			textView.setMovementMethod(LinkMovementMethod.getInstance());
//			textView2.setMovementMethod(LinkMovementMethod.getInstance());
//			textView3.setMovementMethod(LinkMovementMethod.getInstance());
//			textView4.setMovementMethod(LinkMovementMethod.getInstance());
//			textView5.setMovementMethod(LinkMovementMethod.getInstance());
			textView6.setMovementMethod(LinkMovementMethod.getInstance());
			
			
			
			
			// 以下代码为卢伊林在2014年2月28日希望通过Intent传递数据加载activity_menu页；
			
			// textView = (TextView) this.findViewById(R.id.msg);
			// Intent intent = getIntent();
			// int age = intent.getIntExtra("age", 0);
			// String name = intent.getStringExtra("name");
			// String address = intent.getStringExtra("address");

			// textView.setText("age--->>" + age + "\n" + "name--->>" + name
			// + "address--->>" + address);	
			
			
			
			
			
			
		}
		
	}
	
		
