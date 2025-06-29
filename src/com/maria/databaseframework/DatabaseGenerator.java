package com.maria.databaseframework;

import java.util.ArrayList;

public class DatabaseGenerator implements DatabaseGeneratorInterface{

    private String name;
    private ArrayList <TableGeneratorInterface> tableGeneratorList;
    
	public DatabaseGenerator(String name, ArrayList<TableGeneratorInterface> tableGeneratorList) {
		setName(name);
		setTableGeneratorList(tableGeneratorList);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList <TableGeneratorInterface> getTableGeneratorList() {
		return tableGeneratorList;
	}

	public void setTableGeneratorList(ArrayList<TableGeneratorInterface> tableGeneratorList) {
		this.tableGeneratorList = tableGeneratorList;
	}

	public void executeScript() {}

	public String generateScript() {
		return "";
	}

	public void generateValidationTableName() throws Exception {}

	public ArrayList <TableGeneratorInterface> generateInsertionOrder() throws Exception {
		return getTableGeneratorList();
	}
}
