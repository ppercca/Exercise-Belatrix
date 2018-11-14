package com.example.demo.joblogger;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.logtraffic.LogTraffic;
import com.example.demo.logtraffic.LogTrafficRepository;

public class JobLogger {
	
	@Autowired
	private static LogTrafficRepository logTrafficRepository;
	
	private static Map dbParams;
	private static Logger logger;
	private static LoggerConstants loggerType;
	
	JobLogger jobLogger;
	
	public JobLogger(Map dbParams, LoggerConstants loggerType) {
		this.dbParams = dbParams;
		JobLogger.logger = Logger.getLogger("MyLog");
		this.loggerType = loggerType;
	}
	
	public static void LogMessage(String messageText, LoggerMethods loggerMethod) throws Exception {
		messageText.trim();
		StringJoiner stringJoiner = new StringJoiner(" - ");
		stringJoiner.add(loggerType.getType());
		stringJoiner.add(DateFormat.getDateInstance(DateFormat.LONG).format(new Date()));
		stringJoiner.add(messageText);
		messageText = stringJoiner.toString();
				
		if (messageText != null && !messageText.isEmpty()) {
			switch (loggerMethod) {
				case DB: logDB(dbParams, messageText); break;
				case FILE: logFile(dbParams, messageText); break;
				case CONSOLE: logConsole(dbParams, messageText); break;
				default: throw new Exception("Invalid configuration");				
			}				
		}		
	}
	
	private static void logDB(Map params, String messageText) throws SQLException {
		LogTraffic logTraffic = buildLogTraffic(params, messageText);
		logTraffic = logTrafficRepository.save(logTraffic);
	}
	
	private static LogTraffic buildLogTraffic(Map params, String messageText) {
		LogTraffic logTraffic = new LogTraffic();
		logTraffic.setDate(new Date());
		logTraffic.setMessage(messageText);
		logTraffic.setType("INFO");
		return logTraffic;
	}

	public static void logFile(Map params, String messageText) throws IOException {
		File logFile = new File(dbParams.get("logFileFolder") + "/logFile.txt");
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
		
		FileHandler fh = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");
		logger.addHandler(fh);
		logger.log(Level.INFO, messageText);
	}
	
	public static void logConsole (Map params, String messageText) {
		ConsoleHandler ch = new ConsoleHandler();
		logger.addHandler(ch);
		logger.log(Level.INFO, messageText);
	}
	
}
