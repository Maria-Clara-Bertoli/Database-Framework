package com.maria.databaseframework;

import java.util.ArrayList;

public class SimpleTable{
	
	private String name;
    private String primaryKey;
    private ArrayList <FieldGenerator> fieldList;

	public SimpleTable(String name, String primaryKey, ArrayList <FieldGenerator> fieldList) {
		setName(name);
		setPrimaryKey(primaryKey);
		setFieldList(fieldList);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public ArrayList<FieldGenerator> getFieldList() {
		return fieldList;
	}

	public void setFieldList(ArrayList <FieldGenerator> fieldList) {
		this.fieldList = fieldList;
	}
}
