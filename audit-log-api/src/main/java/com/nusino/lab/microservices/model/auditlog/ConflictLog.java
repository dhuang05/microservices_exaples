package com.nusino.lab.microservices.model.auditlog;

import java.util.ArrayList;
import java.util.List;

public class ConflictLog {
	private List<AuditLog> errorGroup = new ArrayList<>();
	private List<AuditLog> conflictList = new ArrayList<>();
	
	public List<AuditLog> getErrorGroup() {
		return errorGroup;
	}
	public void setErrorGroup(List<AuditLog> errorGroup) {
		this.errorGroup = errorGroup;
	}
	public List<AuditLog> getConflictList() {
		return conflictList;
	}
	public void setConflictList(List<AuditLog> conflictList) {
		this.conflictList = conflictList;
	}

	
	
}
