package com.example.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.joblogger.JobLogger;
import com.example.demo.joblogger.LoggerConstants;
import com.example.demo.joblogger.LoggerMethods;
import com.example.demo.logtraffic.LogTraffic;
import com.example.demo.logtraffic.LogTrafficRepository;
@RunWith(SpringRunner.class)
@DataJpaTest
public class LogTest {
	@Autowired
	private LogTrafficRepository logTrafficRepository;
	
	
	@Test
	public void testLogConsole () {
		Map<String, String> params = new HashMap<String, String>();
		JobLogger jobLogger = new JobLogger(params, LoggerConstants.MESSAGE);
		try {
			System.out.println("CONSOLE - START");
			JobLogger.LogMessage("Hola mundo", LoggerMethods.CONSOLE);
			System.out.println("CONSOLE - END");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLogFile () {
		Map<String, String> params = new HashMap<String, String>();
		params.put("logFileFolder", ".");
		JobLogger jobLogger = new JobLogger(params, LoggerConstants.MESSAGE);
		try {
			JobLogger.LogMessage("Hola mundo", LoggerMethods.FILE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLogDB() {
		Map<String, String> params = new HashMap<String, String>();
		JobLogger jobLogger = new JobLogger(params, LoggerConstants.MESSAGE);
		try {
			jobLogger.LogMessage("Hola mundo", LoggerMethods.DB);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void persistLog() {
		LogTraffic logTraffic = new LogTraffic();
		logTraffic.setDate(new Date());
		logTraffic.setMessage("Hola mundo");
		logTraffic.setType("INFO");
		logTraffic = logTrafficRepository.save(logTraffic);
		System.out.println("LogTraffic ID -> "+ logTraffic.getId());
	}
	

}
