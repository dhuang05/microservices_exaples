package com.nusino.lab.microservices.streams.common;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

import com.nusino.lab.microservices.config.common.RequestInfoKeeper;
import com.nusino.lab.microservices.model.common.AuditLog;
import com.nusino.lab.microservices.model.common.OperationType;
import com.nusino.lab.microservices.util.common.JsonBuilder;

@EnableBinding(AuditLogOutputStreams.class)
public class AuditLogProducer {

	private AuditLogOutputStreams auditLogStreams;

	public AuditLogProducer(AuditLogOutputStreams auditLogStreams) {
		this.auditLogStreams = auditLogStreams;
	}

	 public <T> void auditError(Class<T> entityClass, Object entityIdObj, Object dataObject, String desc) {
		 AuditLog auditLog = new AuditLog(RequestInfoKeeper.getGlobalRequestId());
		 auditLog.setOperationType(OperationType.ROLLBACK);
		 if(entityClass != null) {
			 auditLog.setEntityName(entityClass.getSimpleName());
			 if( entityIdObj != null) {
				 auditLog.setEntityId(entityIdObj.toString());
			 }
		 }
		 if(dataObject != null) {
			 auditLog.setJsonData(JsonBuilder.map().toJson(dataObject));
		 }
		 auditLog.setDescription(desc);
		 send(auditLog);
	 }
	
	 public <T> void auditError(Class<T> entityClass, Object entityIdObj, String jsonData, String desc) {
		 AuditLog auditLog = new AuditLog(RequestInfoKeeper.getGlobalRequestId());
		 auditLog.setOperationType(OperationType.ROLLBACK);
		 if(entityClass != null) {
			 auditLog.setEntityName(entityClass.getSimpleName());
			 if( entityIdObj != null) {
				 auditLog.setEntityId(entityIdObj.toString());
			 }
		 }
		 auditLog.setJsonData(jsonData);
		 auditLog.setDescription(desc);
		 send(auditLog);
	 }

	 public <T> void auditLog(OperationType operationType, Class<T> entityClass, Object entityIdObj, String jsonData, String desc) {
		 send(new AuditLog(RequestInfoKeeper.getGlobalRequestId(), operationType, entityClass != null? entityClass.getSimpleName() : null, entityIdObj, jsonData, desc));
	 }

	 public <T> void auditLog(OperationType operationType, Class<T> entityClass, Object entityIdObj, Object dataObject, String desc) {
		 send(new AuditLog(RequestInfoKeeper.getGlobalRequestId(), operationType, entityClass != null? entityClass.getSimpleName() : null, entityIdObj, JsonBuilder.map().toJson(dataObject), desc));
	 }
	
	private void send(AuditLog payload) {
		auditLogStreams.output()
		.send(MessageBuilder.withPayload(payload)
		.setHeader("type", "json")
		.build());
		
	}
}