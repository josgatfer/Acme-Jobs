
package acme.features.employer.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/application/")
public class EmployerApplicationController extends AbstractController<Employer, Application> {

	@Autowired
	private EmployerApplicationListMineService			listMineService;

	@Autowired
	private EmployerApplicationListMineReferenceService	listMineReferenceService;

	@Autowired
	private EmployerApplicationListMineStatusService	listMineStatusService;

	@Autowired
	private EmployerApplicationListMineMomentService	listMineMomentService;

	@Autowired
	private EmployerApplicationShowService				showService;

	@Autowired
	private EmployerApplicationUpdateAccept				updateAccept;

	@Autowired
	private EmployerApplicationUpdateNoAccept			updateNoAccept;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_MINE_REFERENCE, BasicCommand.LIST, this.listMineReferenceService);
		super.addCustomCommand(CustomCommand.LIST_MINE_STATUS, BasicCommand.LIST, this.listMineStatusService);
		super.addCustomCommand(CustomCommand.LIST_MINE_MOMENT, BasicCommand.LIST, this.listMineMomentService);
		super.addCustomCommand(CustomCommand.UPDATE_ACCEPT, BasicCommand.UPDATE, this.updateAccept);
		super.addCustomCommand(CustomCommand.UPDATE_NO_ACCEPT, BasicCommand.UPDATE, this.updateNoAccept);
	}

}
