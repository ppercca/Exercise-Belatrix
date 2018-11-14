package com.example.demo.joblogger;

public enum LoggerMethods {
	FILE(1),
	DB(2),
	CONSOLE(3);
	
	private int id;
	
	private LoggerMethods (int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
