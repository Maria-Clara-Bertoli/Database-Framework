package com.maria.databaseframework;

public class SimpleTableGeneratorFactoryMySQL implements SimpleTableGeneratorFactoryInterface{
	
	public SimpleTableGenerator createSimpleTableGenerator(SimpleTable simpleTable) {
		return new SimpleTableGeneratorMySQL(simpleTable);
	}
}
