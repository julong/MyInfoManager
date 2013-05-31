package com.julong.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.julong.DB.Database;

public abstract class DBHelper extends SQLiteOpenHelper {

	private static final String DBNAME = "FOODSHAKE.db";
	private static final int version = 1;
	private static final String LOG_TAG = "DB_";

	public DBHelper(Context context) {
		super(context, DBNAME, null, version);
	}

	public abstract String getTableName();

	public abstract String getId();

	public int insert(ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		long lastId = db.insert(getTableName(), null, values);
		db.close();
		logger("娣诲姞鏁版嵁id:" + lastId);
		return (int) lastId;
	}

	/**
	 * 淇敼璁板綍
	 * 
	 * @param values
	 *            鏁版嵁鍊�
	 * @param id
	 *            涓婚敭id
	 */
	public void update(ContentValues values, int id) {
		SQLiteDatabase db = getWritableDatabase();
		db.update(getTableName(), values, getId() + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
		logger("淇敼鏁版嵁id:" + id);
	}

	/**
	 * 鍒犻櫎璁板綍
	 * 
	 * @param id
	 *            瑕佸垹闄よ褰曠殑id
	 */
	public void delete(int id) {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(getTableName(), getId() + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
		logger("鍒犻櫎id:" + id);
	}

	/**
	 * 鍒犻櫎鎵�湁
	 */
	public void deleteAll() {
		SQLiteDatabase db = getWritableDatabase();
		int num = db.delete(getTableName(), null, null);
		db.close();
		logger("鍒犻櫎鎵�湁,鍏辫" + num);
	}

	/**
	 * 鏌ヨ鎵�湁
	 * 
	 * @param orderBy
	 *            鎺掑簭
	 * @return
	 */
	public List<Map<String, String>> queryAll(String orderBy) {
		logger("鏌ヨ鎵�湁璁板綍");
		return this.limit(0, 0, orderBy);
	}

	/**
	 * 鏍规嵁ID鑾峰彇涓�潯璁板綍
	 * 
	 * @param id
	 *            涓婚敭id
	 * @return
	 */
	public Map<String, String> get(int id) {
		logger("鑾峰彇璁板綍id:" + id);
		return getOne(getId(), String.valueOf(id));
	}

	/**
	 * 鏍规嵁瀛楁鑾峰彇涓�潯璁板綍
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public Map<String, String> getOne(String name, String value) {
		logger("鑾峰彇璁板綍" + name + ":" + value);
		SQLiteDatabase db = getReadableDatabase();
		Cursor cs = db.query(getTableName(), null, name + " = ?",
				new String[] { value }, null, null, null, "0,1");
		if (cs.moveToNext()) {
			Map<String, String> item = new HashMap<String, String>();
			String[] columnNames = cs.getColumnNames();
			for (String columnName : columnNames) {
				item.put(columnName,
						cs.getString(cs.getColumnIndex(columnName)));
			}
			cs.close();
			return item;
		} else {
			cs.close();
			return null;
		}
	}

	/**
	 * 鍒嗘鏌ヨ
	 * 
	 * @param start
	 *            寮�绱㈠紩
	 * @param size
	 *            璁板綍鏉℃暟
	 * @param orderBy
	 * @return
	 */
	public List<Map<String, String>> limit(int start, int size, String orderBy) {
		SQLiteDatabase db = getReadableDatabase();
		List<Map<String, String>> result = null;

		start = start < 0 ? 0 : start;
		size = size < 0 ? 0 : size;
		String limit = size > 0 ? start + "," + size : null;

		logger("鍒嗘鏌ヨ" + limit);
		Cursor cs = db.query(getTableName(), null, null, null, null, null,
				orderBy, limit);

		if (cs == null || cs.getCount() <= 0) {
			return new ArrayList<Map<String, String>>();
		}

		result = cursor2MapList(cs);
		cs.close();
		return result;
	}

	/**
	 * 鎸夊瓧娈靛悕鏌ヨ
	 * 
	 * @param name
	 *            瀛楁鍚�
	 * @param value
	 *            瀛楁鍊�
	 * @return
	 */
	public List<Map<String, String>> queryByName(String name, String value) {
		SQLiteDatabase db = getReadableDatabase();
		List<Map<String, String>> result = null;

		Cursor cs = db.query(getTableName(), null, name + "= ?",
				new String[] { value }, null, null, null);

		if (cs == null || cs.getCount() <= 0) {
			return new ArrayList<Map<String, String>>();
		}

		result = cursor2MapList(cs);
		cs.close();
		return result;
	}

	/**
	 * 鏍规嵁sql鏌ヨ
	 * 
	 * @param sql
	 *            sql璇彞
	 * @param value
	 *            鏌ヨ鏉′欢鍊�
	 * @return
	 */
	public List<Map<String, String>> queryBySql(String sql, String[] strArgs) {
		SQLiteDatabase db = getReadableDatabase();
		List<Map<String, String>> result = null;
		Cursor cs = db.rawQuery(sql, strArgs);

		if (cs == null || cs.getCount() <= 0) {
			return new ArrayList<Map<String, String>>();
		}

		result = cursor2MapList(cs);
		cs.close();
		db.close();
		return result;
	}

	protected List<Map<String, String>> cursor2MapList(Cursor cs) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		while (cs.moveToNext()) {
			Map<String, String> item = new HashMap<String, String>();
			String[] columnNames = cs.getColumnNames();
			for (String columnName : columnNames) {
				item.put(columnName,
						cs.getString(cs.getColumnIndex(columnName)));
			}
			result.add(item);
		}
		return result;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Database.CREATE_TABLE);
		Log.i(LOG_TAG + "create", "姝ｅ湪寤鸿〃...");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(Database.CREATE_TABLE);
		Log.i(LOG_TAG + "create", "onUpgrade...");
	}

	/**
	 * 鍐欐棩蹇�
	 * 
	 * @param log
	 *            鏃ュ織鍐呭
	 */
	private void logger(String log) {
		Log.i(LOG_TAG + getTableName(), log);
	}
}
