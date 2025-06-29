package com.maria.databaseframework;

public class FieldGenerator implements FieldGeneratorInterface{
	
	private int size;
    private String name;
    private FieldType type;
    private boolean notNull;
    
    public FieldGenerator(int size, String name, FieldType type, boolean notNull) {
    	setSize(size);
    	setName(name);
    	setType(type);
    	setNotNull(notNull);
    }
    
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public FieldType getType() {
		return type;
	}
	
	public void setType(FieldType type) {
		this.type = type;
	}
	
	public boolean getNotNull() {
		return notNull;
	}
	
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	
	public String generateScript() {
		return "";
	}
}
