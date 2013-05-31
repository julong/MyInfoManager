package com.julong.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.julong.DB.Database;
import com.julong.infomanager.R;

public class ContentAdapter extends BaseAdapter {
	private LayoutInflater li = null;
	private List<Map<String, String>> list = null;

	public ContentAdapter(Context context, List<Map<String, String>> list) {
		this.list = list;
		this.li = LayoutInflater.from(context);
	}

	static class Holder {
		TextView name;
		TextView value;
	}

	@Override
	public int getCount() {
		return this.list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			convertView = this.li.inflate(R.layout.content_item, null);
			holder = new Holder();
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.value = (TextView) convertView.findViewById(R.id.value);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.name.setText(this.list.get(position).get(Database.value1));
		holder.value.setText(this.list.get(position).get(Database.value2));
		return convertView;
	}

}
