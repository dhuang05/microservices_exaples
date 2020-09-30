package com.nusino.lab.microservices.controller.auditlog;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nusino.lab.microservices.model.auditlog.AuditLog;
import com.nusino.lab.microservices.model.auditlog.ConflictLog;
import com.nusino.lab.microservices.service.auditlog.AuditLogService;


@RestController
@RequestMapping("api")
public class AuditLogController {
	
	@Autowired
	private AuditLogService auditLogService;
	
	@GetMapping(path = "logs/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AuditLog> fetchLogs(@PathVariable(name="date", required=false)  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		Date since = null;
		if(date == null) {
			since = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
			
		} else {
			ZoneId defaultZoneId = ZoneId.systemDefault();
			since = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
			
		}
		return auditLogService.fetchLogs(since);
		
	}
	
	@GetMapping(path = "conflictLogs/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ConflictLog> conflictLogs(@PathVariable(name="date", required=false)  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		Date since = null;
		if(date == null) {
			since = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
			
		} else {
			ZoneId defaultZoneId = ZoneId.systemDefault();
			since = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
			
		}
		
		return auditLogService.conflictLogs(since);

	}
	

}
