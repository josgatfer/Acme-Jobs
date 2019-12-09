
package acme.features.auditor.auditRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/job-audit-record")
public class AuditorJobsWithAuditRecordController extends AbstractController<Auditor, Job> {

	@Autowired
	private AuditorJobsWithAuditRecordListMineService	listService;

	@Autowired
	private AuditorJobsAuditRecordShowService		showService;


	@PostConstruct
	private void inicialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);

	}
}
