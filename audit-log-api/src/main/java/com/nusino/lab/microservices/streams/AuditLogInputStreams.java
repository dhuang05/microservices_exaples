package com.nusino.lab.microservices.streams;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;  

public interface AuditLogInputStreams {
	String INPUT = "auditlog-input";
	//String OUTPUT = "auditlog-output";

	@Input(INPUT)
	SubscribableChannel input();

	//@Output(OUTPUT)
	//MessageChannel output();
}
