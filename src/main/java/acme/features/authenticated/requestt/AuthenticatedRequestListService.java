
package acme.features.authenticated.requestt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Requestt;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedRequestListService implements AbstractListService<Authenticated, Requestt> {

	@Autowired
	AuthenticatedRequestRepository repository;


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
	public Collection<Requestt> findMany(final Request<Requestt> request) {
		assert request != null;

		Collection<Requestt> result;

		result = this.repository.findManyAll();

		return result;
	}

}
