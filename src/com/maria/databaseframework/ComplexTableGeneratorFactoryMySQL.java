package com.maria.databaseframework;

public class ComplexTableGeneratorFactoryMySQL implements ComplexTableGeneratorFactoryInterface{
	
	public ComplexTableGenerator createSimpleDependencyComplexTableGenerator(ComplexTable complexTable, SimpleTable dependencySimpleTable) {
		return new ComplexTableGeneratorMySQL(complexTable, dependencySimpleTable);
	}
	
	public ComplexTableGenerator createComplexDependencyComplexTableGenerator(ComplexTable complexTable, ComplexTable dependencyComplexTable) {
		return new ComplexTableGeneratorMySQL(complexTable, dependencyComplexTable);
	}

}
