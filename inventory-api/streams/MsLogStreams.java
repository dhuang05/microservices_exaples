package com.nusino.lab.microservices.streams;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;  

public interface MsLogStreams {
	String INPUT = "log-input";
	String OUTPUT = "log-output";

	@Input(INPUT)
	SubscribableChannel input();

	@Output(OUTPUT)
	MessageChannel output();
}
