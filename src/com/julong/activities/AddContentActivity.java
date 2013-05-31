package com.julong.activities;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.julong.DB.Database;
import com.julong.infomanager.R;

public class AddContentActivity extends Dialog implements View.OnClickListener {

	private Context context = null;
	private Button commit = null;

	private EditText name = null;
	private EditText value = null;

	public AddContentActivity(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.ativity_add_content);

		name = (EditText) this.findViewById(R.id.name);
		value = (EditText) this.findViewById(R.id.value);
		commit = (Button) this.findViewById(R.id.commit);
		commit.setOnClickListener(this);
	}

	private void insertData() {
		if (("".equals(name.getText().toString().trim()))
				|| ("".equals(value.getText().toString().trim()))) {
			return;
		} else {
			ContentValues values = new ContentValues();
			values.put(Database.dataType, 1);
			values.put(Database.key, 1);
			values.put(Database.value1, name.getText().toString().trim());
			values.put(Database.value2, value.getText().toString().trim());
			Database.insert(context, values);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.commit:
			insertData();
			this.dismiss();
			break;
		}
	}

}
