package com.maria.databaseframework;

import java.util.ArrayList;

public class ComplexTable extends SimpleTable{

    private String foreignKey;

	public ComplexTable(String name, String primaryKey, ArrayList <FieldGenerator> fieldList, String foreignKey) {
		super(name, primaryKey, fieldList);
		setForeignKey(foreignKey);
	}

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}
}