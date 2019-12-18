
package acme.features.authenticated.messages;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.messages.Messages;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/messages/")
public class AuthenticatedMessageController extends AbstractController<Authenticated, Messages> {

	@Autowired
	private AuthenticatedMessageShowService showService;

	@Autowired
	private AuthenticatedMessageListService listService;

	@Autowired
	private AuthenticatedMessageCreateService createService;

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
