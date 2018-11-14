package com.example.demo.joblogger;

public enum LoggerConstants {

	MESSAGE("message", 1),
	ERROR("error", 2),
	WARNING("warning", 3);
	
	private String type;
	private Integer id;

	private LoggerConstants(String type, Integer id) {
		this.type = type;
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
