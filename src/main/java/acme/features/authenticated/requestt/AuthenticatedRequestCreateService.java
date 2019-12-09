
package acme.features.authenticated.requestt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Requestt;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedRequestCreateService implements AbstractCreateService<Authenticated, Requestt> {

	@Autowired
	AuthenticatedRequestRepository repository;


	@Override
	public boolean authorise(final Request<Requestt> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Requestt> request, final Requestt entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Requestt> request, final Requestt entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "reward", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Requestt instantiate(final Request<Requestt> request) {
		Requestt result;

		result = new Requestt();
		return result;
	}

	@Override
	public void validate(final Request<Requestt> request, final Requestt entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		char currency;
		System.out.println(request.getModel().getString("reward"));
		if (request.getModel().getString("reward") != "") {
			int length = request.getModel().getString("reward").length();
			currency = request.getModel().getString("reward").charAt(length - 1);
			errors.state(request, currency == '€', "reward", "authenticated.request.error.eur");
		}
		boolean isAccepted;
		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.request.error.must-accept");
	}

	@Override
	public void create(final Request<Requestt> request, final Requestt entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
