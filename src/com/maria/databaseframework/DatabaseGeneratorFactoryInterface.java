package com.maria.databaseframework;

import java.util.ArrayList;

public interface DatabaseGeneratorFactoryInterface {
	
	public DatabaseGenerator createDatabaseGenerator(String name, ArrayList <TableGeneratorInterface> tableGeneratorList);
	
}
