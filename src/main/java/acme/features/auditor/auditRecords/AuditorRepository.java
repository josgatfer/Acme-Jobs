
package acme.features.auditor.auditRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j")
	Collection<Job> findAllJobs();

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select a from AuditRecord a where a.auditor.id = ?1")
	Collection<AuditRecord> findManyByAuditorId(int auditorId);

	@Query("select j from Job j where j.id = ?1")
	Job findJobByAuditRecordId(int auditRecordId);

	@Query("select j from Job j where j.id != ?1")
	Collection<Job> findJobWithoutAuditRecordId(int auditRecordId);

	@Query("select a from AuditRecord a where a.job.id = ?1")
	Collection<AuditRecord> findAuditRecordsByJobId(int jobId);

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findOneAuditorById(int auditorId);

}
