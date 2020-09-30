package com.nusino.lab.microservices.streams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.nusino.lab.microservices.model.auditlog.AuditLog;
import com.nusino.lab.microservices.service.auditlog.AuditLogService;

@EnableBinding(AuditLogInputStreams.class)
public class AuditLogConsumer {

	@Autowired
	private AuditLogService auditLogService;
	
	@StreamListener(target = AuditLogInputStreams.INPUT)
	public void handleMsLog(@Payload AuditLog auditLog) {
		auditLogService.log(auditLog);
	}
}
