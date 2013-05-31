package com.julong.DB;

import android.content.ContentValues;
import android.content.Context;

import com.julong.tools.DBHelper;

public class Database extends DBHelper {
	public final static String TABLE_NAME = "DataA";
	public final static String id = "ID";
	public final static String dataType = "DATATYPE";
	public final static String key = "KEY";
	public final static String value1 = "VALUE1";
	public final static String value2 = "VALUE2";
	public final static String value3 = "VALUE3";
	public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
			+ " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT," + dataType
			+ " INTEGER," + key + " INTEGER," + value1 + " TEXT," + value2
			+ " TEXT," + value3 + " TEXT" + ")";

	public Database(Context context) {
		super(context);
	}

	public static int insert(Context context, ContentValues values) {
		Database db = new Database(context);
		return db.insert(values);
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getId() {
		return id;
	}

}
