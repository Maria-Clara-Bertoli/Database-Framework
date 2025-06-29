package com.maria.databaseframework;

public class FieldGeneratorFactoryMySQL implements FieldGeneratorFactoryInterface{
	
	public FieldGenerator createFieldGenerator(int size, String name, FieldType type, boolean notNull) {
		return new FieldGeneratorMySQL(size, name, type, notNull);
	}
}
