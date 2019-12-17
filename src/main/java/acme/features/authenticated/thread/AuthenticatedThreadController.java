
package acme.features.authenticated.thread;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

import acme.entities.threads.Thread;

@Controller
@RequestMapping("/authenticated/thread/")
public class AuthenticatedThreadController extends AbstractController<Authenticated, Thread> {

	@Autowired
	private AuthenticatedThreadListService listService;

	@Autowired
	private AuthenticatedThreadShowService showService;
	
	@Autowired
	private AuthenticatedThreadCreateService createService;
	
	@Autowired
	private AuthenticatedThreadUpdateAddUserService updateAddUserService;
	
	@Autowired
	private AuthenticatedThreadUpdateDeleteUserService updateDeleteUserService;

	@PostConstruct
	private void inicialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.ADD_USER, BasicCommand.UPDATE, this.updateAddUserService);
		super.addCustomCommand(CustomCommand.DELETE_USER, BasicCommand.UPDATE, this.updateDeleteUserService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
