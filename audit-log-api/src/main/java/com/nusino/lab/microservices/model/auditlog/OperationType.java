package com.nusino.lab.microservices.model.auditlog;

public enum OperationType {
	INSERT,
	UPDATE,
	PRE_UPDATE,
	POST_UPDATE,
	DELETE,
	READ,
	ROLLBACK,
	INFO
}
