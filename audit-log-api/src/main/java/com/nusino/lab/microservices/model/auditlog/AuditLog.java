package com.nusino.lab.microservices.model.auditlog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "AUDIT_LOG")
public class AuditLog {
	@Id 
	@GeneratedValue
	 @Column(name="log_id")
	 private Long logId;
	
	@Column(name="global_request_id")
	 private String globalRequestId;
	
	
	@Column(name="operation_type")
	@Enumerated(EnumType.STRING)
	 private OperationType operationType;
	 
	@Column(name="entity_name")
	 private String entityName;
	
	 
	@Column(name="entity_id")
	 private String entityId;


	@Column(name="nano_stamp")
	 private Long nanoStamp = System.nanoTime();
	 
	 
	@Column(name="event_stamp")
	 private Date eventStamp = new Date();
	
	@Column(name="description")
	 private String description;
	 
	@Column(name="json_data")
	 private String jsonData;

	 
	 public AuditLog() {
	 }

	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	public Long getNanoStamp() {
		return nanoStamp;
	}
	public void setNanoStamp(Long nanoStamp) {
		this.nanoStamp = nanoStamp;
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

	public Date getEventStamp() {
		return eventStamp;
	}
	public void setEventStamp(Date eventStamp) {
		this.eventStamp = eventStamp;
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

	public String getGlobalRequestId() {
		return globalRequestId;
	}

	public String getGlobalRequesId() {
		return globalRequestId;
	}

	public void setGlobalRequestId(String globalRequestId) {
		this.globalRequestId = globalRequestId;
	}

	
}
