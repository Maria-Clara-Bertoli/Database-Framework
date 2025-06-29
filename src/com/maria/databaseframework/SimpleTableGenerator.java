package com.maria.databaseframework;

public class SimpleTableGenerator implements TableGeneratorInterface {

    private SimpleTable simpleTable;
    
    public SimpleTableGenerator (SimpleTable simpleTable) {
        setSimpleTable(simpleTable);
    }

    public SimpleTable getSimpleTable() {
		return simpleTable;
    }
    
	public void setSimpleTable(SimpleTable simpleTable) {
		this.simpleTable = simpleTable;
	}
	
	public String generateScript() throws Exception {
        return "";
    }
}
