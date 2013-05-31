package com.julong.activities;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.julong.DB.Database;
import com.julong.adapter.ContentAdapter;
import com.julong.infomanager.R;
 
public class ContentActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	private Button addContent = null;
	private Button motifyPwd = null;

	private ListView listview = null;
	private List<Map<String, String>> list = null;
	private Intent intent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		intent = new Intent();
		setContentView(R.layout.activity_content);

		listview = (ListView) findViewById(R.id.content_listview);
		listview.setOnItemClickListener(this);
		addContent = (Button) this.findViewById(R.id.add_content_btn);
		motifyPwd = (Button) this.findViewById(R.id.motify_btn);
		addContent.setOnClickListener(this);
		motifyPwd.setOnClickListener(this);

		loadData();
	}

	private void loadData() {
		String sql = "select * from " + Database.TABLE_NAME + " where "
				+ Database.dataType + "=1 and " + Database.key + "=1";
		Database d = new Database(this);
		this.list = d.queryBySql(sql, null);
		ContentAdapter adapter = new ContentAdapter(this, this.list);
		this.listview.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_content_btn:
			AddContentActivity add = new AddContentActivity(this,
					R.style.MyDialog);
			add.show();
			break;
		case R.id.motify_btn:
			this.intent.setClass(this, MotifyPwd.class);
			this.startActivity(intent);
			break;
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		loadData();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View convertView,
			int position, long arg3) {
		this.intent.putExtra("id", list.get(position).get(Database.id));
		this.intent.setClass(this, DetailActivity.class);
		this.startActivity(intent);
	}

}
