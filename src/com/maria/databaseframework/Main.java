package com.maria.databaseframework;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ConnectionInformation connectionInformation = ConnectionInformation.getConnectionInformation();
		
		connectionInformation.setPort(3306);
		connectionInformation.setPassword("");
		connectionInformation.setType("mysql");
		connectionInformation.setUserName("root");
		connectionInformation.setHost("localhost");
		
		ArrayList <FieldGenerator> loanFieldGeneratorList = new ArrayList <FieldGenerator> ();
		ArrayList <FieldGenerator> peopleFieldGeneratorList = new ArrayList <FieldGenerator> ();
		ArrayList <FieldGenerator> accountFieldGeneratorList = new ArrayList <FieldGenerator> ();
		ArrayList <TableGeneratorInterface> tableGeneratorList = new ArrayList <TableGeneratorInterface> ();
		
		FieldGeneratorFactoryMySQL fieldGeneratorFactoryMySQL = new FieldGeneratorFactoryMySQL();
		DatabaseGeneratorFactoryMySQL databaseGeneratorFactoryMySQL = new DatabaseGeneratorFactoryMySQL();
		SimpleTableGeneratorFactoryMySQL simpleTableGeneratorFactoryMySQL = new SimpleTableGeneratorFactoryMySQL();
		ComplexTableGeneratorFactoryMySQL complexTableGeneratorFactoryMySQL = new ComplexTableGeneratorFactoryMySQL();
		
		// Criação da tabela people
		
		FieldGenerator peopleIdFieldGenerator = fieldGeneratorFactoryMySQL.createFieldGenerator(0, "id", FieldType.INTEGER, true);
		FieldGenerator peopleNameFieldGenerator = fieldGeneratorFactoryMySQL.createFieldGenerator(20, "name", FieldType.VARCHAR, false);
		
		peopleFieldGeneratorList.add(peopleIdFieldGenerator);
		peopleFieldGeneratorList.add(peopleNameFieldGenerator);
		
		SimpleTable peopleSimpleTable = new SimpleTable("people", "id", peopleFieldGeneratorList);
		
		SimpleTableGenerator peopleSimpleTableGenerator = simpleTableGeneratorFactoryMySQL.createSimpleTableGenerator(peopleSimpleTable);
		
		// Criação da tabela account
		
		FieldGenerator accountIdFieldGenerator = fieldGeneratorFactoryMySQL.createFieldGenerator(0, "id", FieldType.INTEGER, true);
		FieldGenerator accountTypeFieldGenerator = fieldGeneratorFactoryMySQL.createFieldGenerator(20, "type", FieldType.VARCHAR, false);
		FieldGenerator accountPeoleIdFieldGenerator = fieldGeneratorFactoryMySQL.createFieldGenerator(0, "people_id", FieldType.INTEGER, true);
		
		accountFieldGeneratorList.add(accountIdFieldGenerator);
		accountFieldGeneratorList.add(accountTypeFieldGenerator);
		accountFieldGeneratorList.add(accountPeoleIdFieldGenerator);
		
		ComplexTable accountComplexTable = new ComplexTable("account", "id", accountFieldGeneratorList, "people_id");
		
		ComplexTableGenerator accountComplexTableGenerator = complexTableGeneratorFactoryMySQL.createSimpleDependencyComplexTableGenerator(accountComplexTable, peopleSimpleTable);
		
		// Criação da tabela loan
		
		FieldGenerator loanIdFieldGenerator = fieldGeneratorFactoryMySQL.createFieldGenerator(0, "id", FieldType.INTEGER, true);
		FieldGenerator loanValueFieldGenerator = fieldGeneratorFactoryMySQL.createFieldGenerator(0, "value", FieldType.FLOAT, false);
		FieldGenerator loanTypeFieldGenerator = fieldGeneratorFactoryMySQL.createFieldGenerator(20, "type", FieldType.VARCHAR, false);
		FieldGenerator loanAccountIdFieldGenerator = fieldGeneratorFactoryMySQL.createFieldGenerator(0, "account_id", FieldType.INTEGER, true);
		
		loanFieldGeneratorList.add(loanIdFieldGenerator);
		loanFieldGeneratorList.add(loanTypeFieldGenerator);
		loanFieldGeneratorList.add(loanValueFieldGenerator);
		loanFieldGeneratorList.add(loanAccountIdFieldGenerator);
		
		ComplexTable loanComplexTable = new ComplexTable("loan", "id", loanFieldGeneratorList, "account_id");
		
		ComplexTableGenerator loanComplexTableGenerator = complexTableGeneratorFactoryMySQL.createComplexDependencyComplexTableGenerator(loanComplexTable, accountComplexTable);
		
		// Criação da lista de tabelas
		
		tableGeneratorList.add(loanComplexTableGenerator);
		tableGeneratorList.add(peopleSimpleTableGenerator);
		tableGeneratorList.add(accountComplexTableGenerator);
		
		// Criação do banco de dados
		
		DatabaseGenerator databaseGenerator = databaseGeneratorFactoryMySQL.createDatabaseGenerator("frameworkdatabase", tableGeneratorList);
		
		databaseGenerator.executeScript();
		
		String script = databaseGenerator.generateScript();
		
		System.out.println(script);
	}
}
