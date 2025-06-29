package com.maria.databaseframework;

public class ComplexTableGenerator implements TableGeneratorInterface {

	private ComplexTable complexTable;
    private SimpleTable dependencySimpleTable;
    private ComplexTable dependencyComplexTable;
    
	public ComplexTableGenerator (ComplexTable complexTable, SimpleTable dependencySimpleTable) {
		setComplexTable(complexTable);
		setDependencySimpleTable(dependencySimpleTable);
	}
	
	public ComplexTableGenerator (ComplexTable complexTable, ComplexTable dependencyComplexTable) {
		setComplexTable(complexTable);
		setDependencyComplexTable(dependencyComplexTable);
	}

	public ComplexTable getComplexTable() {
		return complexTable;
	}

	public void setComplexTable(ComplexTable complexTable) {
		this.complexTable = complexTable;
	}

	public SimpleTable getDependencySimpleTable() {
		return dependencySimpleTable;
	}

	public void setDependencySimpleTable(SimpleTable dependencySimpleTable) {
		this.dependencySimpleTable = dependencySimpleTable;
	}

	public ComplexTable getDependencyComplexTable() {
		return dependencyComplexTable;
	}

	public void setDependencyComplexTable(ComplexTable dependencyComplexTable) {
		this.dependencyComplexTable = dependencyComplexTable;
	}
	
	public String generateScript() throws Exception {
		return "";
	}
}