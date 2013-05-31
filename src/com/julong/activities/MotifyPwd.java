package com.julong.activities;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.julong.DB.Database;
import com.julong.infomanager.R;

public class MotifyPwd extends Activity implements OnClickListener {

	private Button motifyPwd = null;
	private EditText new_password = null;
	// private EditText original_password = null;
	private Intent intent = null;
	private List<Map<String, String>> list = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_motify_pwd);
		this.intent = new Intent();

		// original_password = (EditText) this
		// .findViewById(R.id.original_password);
		new_password = (EditText) this.findViewById(R.id.new_password);
		motifyPwd = (Button) this.findViewById(R.id.motify_btn);
		motifyPwd.setOnClickListener(this);
	}

	private void setPassword() {
		String sql = "select * from " + Database.TABLE_NAME + " where "
				+ Database.dataType + "=0 and " + Database.key + "=0";
		Database d = new Database(this);
		ContentValues values = new ContentValues();
		values.put(Database.dataType, 0);
		values.put(Database.key, 0);
		values.put(Database.value1, this.new_password.getText().toString());

		this.list = d.queryBySql(sql, null);
		if (this.list == null || this.list.size() == 0) {
			d.insert(values);
		} else {
			String id = list.get(0).get(Database.id);
			d.update(values, Integer.parseInt(id));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.motify_btn:
			setPassword();
			this.intent.setClass(this, LoginActivity.class);
			this.startActivity(intent);
			this.finish();
			break;
		}
	}

}
