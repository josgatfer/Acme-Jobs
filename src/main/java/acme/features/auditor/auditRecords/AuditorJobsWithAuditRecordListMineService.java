
package acme.features.auditor.auditRecords;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorJobsWithAuditRecordListMineService implements AbstractListService<Auditor, Job> {

	@Autowired
	AuditorRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "salary", "deadline");

	}

	@Override
	public Collection<Job> findMany(final Request<Job> request) {
		assert request != null;

		Collection<Job> result = new ArrayList<>();
		Principal principal;
		principal = request.getPrincipal();

		Collection<AuditRecord> audits = this.repository.findManyByAuditorId(principal.getActiveRoleId());

		for (AuditRecord a : audits) {
			Job j = this.repository.findJobByAuditRecordId(a.getJob().getId());
			result.add(j);
		}

		return result;
	}

}
