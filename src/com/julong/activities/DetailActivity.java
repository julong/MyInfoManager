package com.julong.activities;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.julong.DB.Database;
import com.julong.infomanager.R;
import com.julong.tools.PasswordUtil;

public class DetailActivity extends Activity implements OnClickListener {

	private Button back_btn = null;

	private TextView name = null;
	private TextView value = null;
	private TextView other = null;
	private EditText remark = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_detail);

		back_btn = (Button) findViewById(R.id.back_btn);
		back_btn.setOnClickListener(this);

		name = (TextView) findViewById(R.id.content_name);
		value = (TextView) findViewById(R.id.content_value);
		other = (TextView) findViewById(R.id.content_other);
		remark = (EditText) findViewById(R.id.detail_content);

		Intent intent = getIntent();
		String id = intent.getStringExtra("id");
		load(id);
	}

	private void load(String id) {
		String sql = "select * from " + Database.TABLE_NAME + " WHERE ID=" + id;
		Database db = new Database(this);
		List<Map<String, String>> list = db.queryBySql(sql, null);
		if (list != null && list.size() > 0) {
			Map<String, String> data = list.get(0);
			name.setText("名称:" + data.get(Database.value1));
			String card = data.get(Database.value2);
			value.setText("内容:" + data.get(Database.value2));
			StringBuilder sb = new StringBuilder();
			sb.append("其他:\n\n");
			if (card != null && !card.equals("") && card.trim().length() > 12) {
				sb.append("SELECT - " + PasswordUtil.calcSelect(card) + " \n");
				sb.append("NEWPAY - " + PasswordUtil.calcNewPay(card) + " \n");
				sb.append("OLDPAY - " + PasswordUtil.cardOldPwd(card) + " \n");
			}
			other.setText(sb.toString());
			remark.setText(data.get(Database.value3));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_btn:
			this.onBackPressed();
			break;
		}
	}

}
