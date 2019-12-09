
package acme.features.authenticated.requestt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Requestt;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedRequestShowService implements AbstractShowService<Authenticated, Requestt> {

	@Autowired
	private AuthenticatedRequestRepository repository;


	@Override
	public boolean authorise(final Request<Requestt> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Requestt> request, final Requestt entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "description", "reward", "deadline", "ticker");

	}

	@Override
	public Requestt findOne(final Request<Requestt> request) {
		assert request != null;

		Requestt result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
