package com.maria.databaseframework;

import java.util.ArrayList;

public class DatabaseGeneratorFactoryMySQL {
	
	public DatabaseGenerator createDatabaseGenerator(String name, ArrayList <TableGeneratorInterface> tableGeneratorList) {
		return new DatabaseGeneratorMySQL(name, tableGeneratorList);
	}
}
