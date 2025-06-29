package com.maria.databaseframework;

public interface FieldGeneratorFactoryInterface {
	
	public FieldGenerator createFieldGenerator(int size, String name, FieldType type, boolean notNull);

}
