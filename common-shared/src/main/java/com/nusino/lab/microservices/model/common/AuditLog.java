package com.nusino.lab.microservices.model.common;

public class AuditLog {
	 private String globalRequestId;
	 private OperationType operationType;
	 private String entityName;
	 private String entityId;
	 private String description;
	 private String jsonData;

	 
	 public AuditLog() {
	 }

	 public AuditLog (String globalRequestId) {
		 this.globalRequestId = globalRequestId;
	 }
	 
	 public AuditLog(String globalRequestId, OperationType operationType, String entityName, Object entityIdObj, String jsonData, String description) {
		 this.globalRequestId = globalRequestId;
		 this.operationType = operationType;
		 this.entityName = entityName;
		 if( entityIdObj != null) {
			 this.entityId = entityIdObj.toString();
		 }
		 this.jsonData = jsonData;
		 this.description = description;
	 }

	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	
	public String getGlobalRequestId() {
		return globalRequestId;
	}
	public void setGlobalRequestId(String globalRequestId) {
		this.globalRequestId = globalRequestId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	
}
