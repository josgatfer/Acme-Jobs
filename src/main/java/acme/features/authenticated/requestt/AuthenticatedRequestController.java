
package acme.features.authenticated.requestt;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requests.Requestt;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/requestt/")
public class AuthenticatedRequestController extends AbstractController<Authenticated, Requestt> {

	@Autowired
	private AuthenticatedRequestCreateService	createService;

	@Autowired
	private AuthenticatedRequestListService		listService;

	@Autowired
	private AuthenticatedRequestShowService		showService;


	@PostConstruct
	private void inicialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
