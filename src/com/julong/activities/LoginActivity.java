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
import android.widget.Toast;

import com.julong.DB.Database;
import com.julong.infomanager.R;
public class LoginActivity extends Activity implements OnClickListener {

	private Button login_btn = null;

	private Intent intent = null;
	private EditText password = null;
	private List<Map<String, String>> list = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.intent = new Intent();

		password = (EditText) findViewById(R.id.password);
		login_btn = (Button) this.findViewById(R.id.login_btn);
		login_btn.setOnClickListener(this);

		String sql = "select * from " + Database.TABLE_NAME + " where "
				+ Database.dataType + "=0 and " + Database.key + "=0";
		Database d = new Database(this);
		this.list = d.queryBySql(sql, null);

		if (this.list == null || this.list.size() == 0) {
			this.intent.setClass(this, MotifyPwd.class);
			this.startActivity(intent);
			this.finish();
		}
	}

	int caculate = 0;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:

			String sql = "select * from " + Database.TABLE_NAME + " where "
					+ Database.dataType + "=0 and " + Database.key + "=0";
			Database d = new Database(this);

			this.list = d.queryBySql(sql, null);

			String pwd = this.list.get(0).get(Database.value1);
			if (pwd.equals(this.password.getText().toString())) {
				this.intent.setClass(this, ContentActivity.class);
				this.startActivity(intent);
				this.finish();
			} else {
				caculate++;
				Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}
}
