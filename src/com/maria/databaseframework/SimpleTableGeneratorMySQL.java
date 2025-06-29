package com.maria.databaseframework;

public class SimpleTableGeneratorMySQL extends SimpleTableGenerator implements TableGeneratorInterface{
	
	SimpleTableGeneratorMySQL(SimpleTable simpleTable){
		super(simpleTable);
	}
	
	public String generateScript() throws Exception{
		StringBuilder sql = new StringBuilder();
        boolean existsSimplePrimaryKey = false;

        for (FieldGenerator field : getSimpleTable().getFieldList()) {
            if (field.getName().equalsIgnoreCase(getSimpleTable().getPrimaryKey())) {
                existsSimplePrimaryKey = true;
                break;
            }
        }

        if (!existsSimplePrimaryKey) {
            throw new Exception("Deve ser definida uma chave primária válida para a tabela " + getSimpleTable().getName());
        }

        sql.append(String.format("CREATE TABLE %s (", getSimpleTable().getName().toLowerCase()));

        for (FieldGenerator field : getSimpleTable().getFieldList()) {
            sql.append(field.generateScript()).append(", ");
        }
        
        sql.append(String.format("PRIMARY KEY (%s)", getSimpleTable().getPrimaryKey().toLowerCase()));

        sql.append(");");

        return sql.toString();
	}
}
