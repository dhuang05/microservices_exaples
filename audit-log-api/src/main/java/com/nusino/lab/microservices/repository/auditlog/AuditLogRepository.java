package com.nusino.lab.microservices.repository.auditlog;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nusino.lab.microservices.model.auditlog.AuditLog;



@Repository
public interface AuditLogRepository extends CrudRepository<AuditLog, Long> {
	@Query(value = "SELECT * FROM AUDIT_LOG WHERE event_stamp > ?1 order by event_stamp desc limit 100", nativeQuery = true)
	List<AuditLog> findByLogSinceDate(Date logDate);

}
