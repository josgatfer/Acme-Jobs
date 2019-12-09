
package acme.features.authenticated.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Messages;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Messages> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Messages> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Messages> request, final Messages entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body");
		model.setAttribute("author", entity.getUser().getUserAccount().getUsername());

	}

	@Override
	public Messages findOne(final Request<Messages> request) {

		assert request != null;

		Messages result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
