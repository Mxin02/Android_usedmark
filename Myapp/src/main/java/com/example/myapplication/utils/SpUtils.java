package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpUtils {
	private static SharedPreferences sp;
	private Editor edit;

	public SpUtils(Context con) {
		sp = con.getSharedPreferences("config", Context.MODE_PRIVATE);
		edit = sp.edit();
	};
	public  void PutString(String key, String values) {
		edit.putString(key, values);
		edit.commit();
	}

	public  String getString(String key, String defValues) {
		return sp.getString(key, defValues);
	}

	public  void PutBoolean(String key, Boolean values) {
		edit.putBoolean(key, values);
		edit.commit();
	}


	public  Boolean getBoolean(String key, Boolean defValues) {
		return sp.getBoolean(key, defValues);
	}

	public  void PutInt(String key, int values) {
		edit.putInt(key, values);
		edit.commit();
	}


	public  int getInt(String key, int defValues) {
		return sp.getInt(key, defValues);
	}
}
