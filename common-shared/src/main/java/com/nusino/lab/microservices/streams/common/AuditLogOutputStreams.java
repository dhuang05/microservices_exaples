package com.nusino.lab.microservices.streams.common;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;  

public interface AuditLogOutputStreams {
	//String INPUT = "auditlog-input";
	String OUTPUT = "auditlog-output";

	//@Input(INPUT)
	//SubscribableChannel input();

	@Output(OUTPUT)
	MessageChannel output();
}
