package com.example.myapplication.base;

import java.util.List;

public class NewsCenterBean {
	public List<NewsCenterData> data;
	public List extend;
	public String retcode;
	public class NewsCenterData {
		public List<NewsCenterChildren> children;// Array
		public String id;// 10000
		public String title;// 
		public String type;// 1
	}
	public class NewsCenterChildren {
		public String id;// e 
		public String type;// 1
		public String url;// /10007/list_1.json
		public String title;// ����
	}
}



