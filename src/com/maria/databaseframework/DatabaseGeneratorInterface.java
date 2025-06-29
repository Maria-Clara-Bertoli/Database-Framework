package com.maria.databaseframework;

import java.util.ArrayList;

public interface DatabaseGeneratorInterface {
	
	public void executeScript();
	public String generateScript();
	public void generateValidationTableName() throws Exception;
	public ArrayList <TableGeneratorInterface> generateInsertionOrder() throws Exception;

}
