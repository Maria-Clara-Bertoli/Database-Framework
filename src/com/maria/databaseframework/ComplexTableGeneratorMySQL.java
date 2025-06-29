package com.maria.databaseframework;

public class ComplexTableGeneratorMySQL extends ComplexTableGenerator implements TableGeneratorInterface{
	
	private SimpleTable dependencyTable;
	
	public ComplexTableGeneratorMySQL(ComplexTable complexTable, SimpleTable dependencySimpleTable) {
		super(complexTable, dependencySimpleTable);
	}
	
	public ComplexTableGeneratorMySQL(ComplexTable complexTable, ComplexTable dependencyComplexTable) {
		super(complexTable, dependencyComplexTable);
	}

	public String generateScript() throws Exception {

        StringBuilder sql = new StringBuilder();
        boolean existsDependencyPrimaryKey = false;
        boolean existsComplexPrimaryKey = false;
        boolean existsComplexForeignKey = false;

        if (getDependencySimpleTable() != null) {
            dependencyTable = getDependencySimpleTable();
        } 
        else if (getDependencyComplexTable() != null) {
            dependencyTable = getDependencyComplexTable();
        } 
        else {
            throw new Exception("Não foi possível gerar o script para a tabela " + getComplexTable().getName() + ", pois a dependência deste elemento não existe.");
        }

        for (FieldGenerator field : dependencyTable.getFieldList()) {
            if (field.getName().equalsIgnoreCase(dependencyTable.getPrimaryKey())) {
                existsDependencyPrimaryKey = true;
                break;
            }
        }

        if (!existsDependencyPrimaryKey) {
            throw new Exception("Deve ser definida uma chave primária válida para a tabela de dependência " + dependencyTable.getName());
        }
        
        sql.append(String.format("CREATE TABLE %s (", getComplexTable().getName().toLowerCase()));
        
        for (FieldGenerator field : getComplexTable().getFieldList()) {
            sql.append(field.generateScript()).append(", ");

            if (field.getName().equalsIgnoreCase(getComplexTable().getPrimaryKey())) {
                existsComplexPrimaryKey = true;
            }

            if (field.getName().equalsIgnoreCase(getComplexTable().getForeignKey())) {
                existsComplexForeignKey = true;
            }
        }

        if (!existsComplexPrimaryKey) {
            throw new Exception("Deve ser definida uma chave primária válida para a tabela " + getComplexTable().getName());
        }

        if (!existsComplexForeignKey) {
            throw new Exception("Deve ser definida uma chave estrangeira válida para a tabela " + getComplexTable().getName());
        }
        
        sql.append(String.format("PRIMARY KEY (%s), ", getComplexTable().getPrimaryKey().toLowerCase()));
        sql.append(String.format("FOREIGN KEY (%s) REFERENCES %s (%s), ",
        		getComplexTable().getForeignKey().toLowerCase(),
                dependencyTable.getName().toLowerCase(),
                dependencyTable.getPrimaryKey().toLowerCase()));

        sql.setLength(sql.length() - 2);
        sql.append(");");

        return sql.toString();
    }
}
