package com.nusino.lab.microservices.service.auditlog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nusino.lab.microservices.model.auditlog.AuditLog;
import com.nusino.lab.microservices.model.auditlog.ConflictLog;
import com.nusino.lab.microservices.model.auditlog.OperationType;
import com.nusino.lab.microservices.repository.auditlog.AuditLogRepository;
import com.nusino.lab.microservices.util.common.JsonBuilder;

@Service
public class AuditLogService {
	 private static final Logger log = LoggerFactory.getLogger(AuditLogService.class);
	
	
	@Autowired
	private AuditLogRepository auditLogRepository;
	
	
	@Transactional
	public AuditLog log(AuditLog auditLog) {
		if(auditLog.getEventStamp() == null) {
			auditLog.setEventStamp(new Date());
		}
		if(auditLog.getNanoStamp() == null) {
			auditLog.setNanoStamp(System.nanoTime());
		}

		System.out.println(JsonBuilder.map().toJson(auditLog));
		AuditLog log =   auditLogRepository.save(auditLog);
		return log;
		
	}

	public List<AuditLog> fetchLogs(Date since) {
		return auditLogRepository.findByLogSinceDate(since);
		
	}

	
	public  List<ConflictLog> conflictLogs(Date since) {
		List<AuditLog> logs = fetchLogs(since);
		
		Map<String, AuditLogGroup>  errorGrops = new HashMap<>();
		Map<String, AuditLogGroup>  allGroups = new HashMap<>();

		//sort groups
		for (AuditLog auditLog : logs) {
			Long id = auditLog.getLogId();
			String grid = auditLog.getGlobalRequesId();
			
			AuditLogGroup auditLogGroup = allGroups.get(grid);
			if(auditLogGroup == null) {
				auditLogGroup = new AuditLogGroup();
				auditLogGroup.setEndId(id);
				auditLogGroup.setStartId(id);
				auditLogGroup.getAuditLogs().add(auditLog);
				allGroups.put(grid, auditLogGroup);
			}else{
				auditLogGroup.setEndId(Math.max(id, auditLogGroup.getEndId()));
				auditLogGroup.setStartId(Math.min(id, auditLogGroup.getStartId()));
				auditLogGroup.getAuditLogs().add(auditLog);
			}
			
			if (auditLog.getOperationType() == OperationType.ROLLBACK) {
				errorGrops.put(grid, auditLogGroup);
			}
		}
		
		
		//pick conflict
		for(AuditLogGroup errorGroup : errorGrops.values()) {
			for(AuditLogGroup group : allGroups.values()) {
				if(errorGroup == group || !(errorGroup.inRang(group.startId) && errorGroup.inRang(group.endId))) {
					continue;
				}
				
				for(AuditLog errorLog : errorGroup.auditLogs) {
					for(AuditLog log : group.auditLogs) {
						if(errorLog.getEntityName() == null || errorLog.getEntityId() == null || log.getEntityName() == null 
								|| log.getEntityId() == null || !getKey(errorLog).equals(getKey(log)) 
								|| !(log.getOperationType() == OperationType.INSERT || log.getOperationType() == OperationType.UPDATE 
								|| log.getOperationType() == OperationType.PRE_UPDATE 
								|| log.getOperationType() == OperationType.POST_UPDATE)
								
								) {
							continue;
						}
						
						Long startError = errorLog.getLogId();
						Long start = log.getLogId();
						
						Long endError = errorGroup.getEndId();
						Long end = group.getEndId();
						
						if(start >= startError &&  start <= endError  || end >= startError &&  end <= endError) {
							errorGroup.conflictSet.add(log);
						}
						
					}
				}
			}
			
		}
		
		List<ConflictLog> conflictLogs = new ArrayList<ConflictLog>();
		
		for(AuditLogGroup errorGroup : errorGrops.values()) {
			if(errorGroup.conflictSet.size() > 0) {
				ConflictLog conflictLog = new ConflictLog();
				conflictLog.setErrorGroup(errorGroup.getAuditLogs());
				conflictLog.getConflictList().addAll(errorGroup.conflictSet);
				conflictLogs.add(conflictLog);
			}
		}
		
		return conflictLogs;
		
	}
	
	
	public String getKey(AuditLog auditLog) {
		return auditLog.getEntityName()+ ":" + auditLog.getEntityId();
	}
	
	public static class AuditLogGroup {
		private Long startId;
		private Long endId;
		private String globalRequestId;
		private List<AuditLog> auditLogs = new ArrayList<>();
		
		private Set<AuditLog> conflictSet = new HashSet<>();
		
		public boolean inRang(Long id) {
			return id >= startId && id <= endId;
		}
		
		public Long getEndId() {
			return endId;
		}
		public void setEndId(Long endId) {
			this.endId = endId;
		}
		public String getGlobalRequestId() {
			return globalRequestId;
		}
		public void setGlobalRequestId(String globalRequestId) {
			this.globalRequestId = globalRequestId;
		}
	

		public List<AuditLog> getAuditLogs() {
			return auditLogs;
		}

		public void setAuditLogs(List<AuditLog> auditLogs) {
			this.auditLogs = auditLogs;
		}

		public Long getStartId() {
			return startId;
		}
		public void setStartId(Long startId) {
			this.startId = startId;
		}

		public Set<AuditLog> getConflictSet() {
			return conflictSet;
		}

		public void setConflictSet(Set<AuditLog> conflictSet) {
			this.conflictSet = conflictSet;
		}


		
	}
}
