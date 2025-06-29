package com.maria.databaseframework;

import java.sql.Statement;
import java.util.Iterator;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;

public class DatabaseGeneratorMySQL extends DatabaseGenerator implements DatabaseGeneratorInterface {
    
	public DatabaseGeneratorMySQL(String name, ArrayList<TableGeneratorInterface> tableGeneratorList) {
		super(name, tableGeneratorList);
	}

	public static Connection sgbdConnection() {
	    	
			try {
				
				ConnectionInformation connectionInformation = ConnectionInformation.getConnectionInformation();
			
				String driver = String.format("com.%s.jdbc.Driver", connectionInformation.getType());
				
				String url = String.format("jdbc:%s://%s/", connectionInformation.getType(), connectionInformation.getHost());
						
	    		Class.forName(driver);
	    		
	            return DriverManager.getConnection(url, connectionInformation.getUserName(), connectionInformation.getPassword());
	        } 
			catch (Exception error) {
	        	error.printStackTrace();
	            return null;
	        }
	    }

    public void generateValidationTableName() throws Exception {
        ArrayList <String> tableNames = new ArrayList<>();

        for (TableGeneratorInterface tableGenerator : getTableGeneratorList()) {
        	
            String tableName;
            
            if (tableGenerator instanceof SimpleTableGenerator) {
                tableName = ((SimpleTableGenerator) tableGenerator).getSimpleTable().getName().toLowerCase();
            } 
            else if (tableGenerator instanceof ComplexTableGenerator) {
                tableName = ((ComplexTableGenerator) tableGenerator).getComplexTable().getName().toLowerCase();
            } 
            else {
                throw new Exception("O tipo de gerador é inválido");
            }

            if (tableNames.contains(tableName)) {
                throw new Exception("Há mais de uma tabela apresentando o mesmo nome na lista de tabelas que devem ser inseridas no banco de dados");
            }
            
            tableNames.add(tableName);
        }
    }

    public ArrayList<TableGeneratorInterface> generateInsertionOrder() throws Exception {
    	
    	String dependencyName = "";
    	
    	boolean existsNewTables = false;
        
        ArrayList <String> insertedTableNames = new ArrayList <>();
        ArrayList <TableGeneratorInterface> insertionOrderList = new ArrayList <> ();
        ArrayList <TableGeneratorInterface> remainingTablesList = new ArrayList <> (getTableGeneratorList());

        Iterator <TableGeneratorInterface> iteratorTableGenerator = remainingTablesList.iterator();
        
        while (iteratorTableGenerator.hasNext()) {
        	
            TableGeneratorInterface tableGenerator = iteratorTableGenerator.next();
            
            if (tableGenerator instanceof SimpleTableGenerator) {
                insertionOrderList.add(tableGenerator);
                iteratorTableGenerator.remove();
            }
        }

        while (true) {
            
            for (TableGeneratorInterface tableGenerator : insertionOrderList) {
            	
                if (tableGenerator instanceof SimpleTableGenerator) {
                    insertedTableNames.add(((SimpleTableGenerator) tableGenerator).getSimpleTable().getName().toLowerCase());
                } 
                else if (tableGenerator instanceof ComplexTableGenerator) {
                    insertedTableNames.add(((ComplexTableGenerator) tableGenerator).getComplexTable().getName().toLowerCase());
                }
            }

            ArrayList <TableGeneratorInterface> singleRoundInsertion = new ArrayList<>();

            for (TableGeneratorInterface tableGenerator : remainingTablesList) {
            	
                if (tableGenerator instanceof ComplexTableGenerator) {
                	
                    ComplexTableGenerator complexTableGenerator = (ComplexTableGenerator) tableGenerator;
                    
                    if (complexTableGenerator.getDependencySimpleTable() != null) {
                    	
                    	dependencyName = complexTableGenerator.getDependencySimpleTable().getName().toLowerCase();
                    }
                    else if (complexTableGenerator.getDependencyComplexTable() != null) {
                    	
                    	dependencyName = complexTableGenerator.getDependencyComplexTable().getName().toLowerCase();
                    }
                   
                    if (insertedTableNames.contains(dependencyName)) {
                    	
                        insertionOrderList.add(complexTableGenerator);
                        singleRoundInsertion.add(complexTableGenerator);
                        existsNewTables = true;
                    }
                }
            }

            remainingTablesList.removeAll(singleRoundInsertion);

            if (!existsNewTables && !remainingTablesList.isEmpty()) {
            	throw new Exception("Não foi possível inserir todas as tabelas no banco de dados ou gerar o script SQL, pois há inconsistências na hierarquia de dependências destes elementos");
            } 
            if (!existsNewTables && remainingTablesList.isEmpty()) {
            	break;
            }
            
            existsNewTables = false;

            insertedTableNames.clear();
        }

        return insertionOrderList;
    }

    public String generateScript() {

        StringBuilder sqlBuilder = new StringBuilder();

        try {
            generateValidationTableName();

            ArrayList <TableGeneratorInterface> insertionOrderList = generateInsertionOrder();

            sqlBuilder.append(String.format("CREATE DATABASE %s;", getName().toLowerCase()));
            sqlBuilder.append("\n\n");

            sqlBuilder.append(String.format("USE %s;", getName().toLowerCase()));
            sqlBuilder.append("\n\n");

            for (TableGeneratorInterface tableGeneratorInterface : insertionOrderList) {
                sqlBuilder.append(tableGeneratorInterface.generateScript());
                sqlBuilder.append("\n");
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return sqlBuilder.toString();
    }
    
    public void executeScript() {

        StringBuilder sqlBuilder = new StringBuilder();

        try {
        	
        	generateValidationTableName();

            ArrayList <TableGeneratorInterface> insertionOrderList = generateInsertionOrder();
        	
        	// Obtenção da conexão
			
        	Connection connection = sgbdConnection();
        				
        	// Intancia a interface utilizada para executar scripts sql no banco de dados
        				
        	Statement statement = connection.createStatement();

            sqlBuilder.append(String.format("CREATE DATABASE %s;", getName().toLowerCase()));
            
            statement.execute(sqlBuilder.toString());
            
            sqlBuilder.setLength(0);

            sqlBuilder.append(String.format("USE %s;", getName().toLowerCase()));
            
            statement.execute(sqlBuilder.toString());
            
            sqlBuilder.setLength(0);

            for (TableGeneratorInterface tableGeneratorInterface : insertionOrderList) {
            	
                sqlBuilder.append(tableGeneratorInterface.generateScript());
                
                statement.execute(sqlBuilder.toString());
                
                sqlBuilder.setLength(0);
            }
            
         // Fechamento da conexão
			
         connection.close();

        } 
        catch (Exception error) {
            error.printStackTrace();
        }
    }
}
