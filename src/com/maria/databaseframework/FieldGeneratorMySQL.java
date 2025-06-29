package com.maria.databaseframework;

public class FieldGeneratorMySQL extends FieldGenerator implements FieldGeneratorInterface{
    
    public FieldGeneratorMySQL(int size, String name, FieldType type, boolean notNull) {
    	super(size, name, type, notNull);
    }
    
    public String generateScript() {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append(getName().toLowerCase())
                  .append(" ")
                  .append(getType());

        if (getType().equals(FieldType.VARCHAR)) {
            sqlBuilder.append("(").append(getSize()).append(")");
        }

        if (getNotNull()) {
            sqlBuilder.append(" NOT NULL");
        }

        return sqlBuilder.toString();
    }
}
