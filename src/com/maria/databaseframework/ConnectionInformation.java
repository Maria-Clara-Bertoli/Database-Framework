package com.maria.databaseframework;

public class ConnectionInformation {
	
	private int port;
	private String type;
	private String host;
	private String userName;
	private String password;
	private static ConnectionInformation connectionInformation;
	
	public ConnectionInformation () {
 
    }
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static ConnectionInformation getConnectionInformation() {
		
		if(connectionInformation == null) {
			connectionInformation = new ConnectionInformation();
		}
		return connectionInformation;
	}
}
