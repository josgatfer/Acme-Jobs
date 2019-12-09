
package acme.features.administrator.spamFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamFilters.SpamFilter;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamFilterShowService implements AbstractShowService<Administrator, SpamFilter> {

	@Autowired
	private AdministratorSpamFilterRepository repository;


	@Override
	public boolean authorise(final Request<SpamFilter> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<SpamFilter> request, final SpamFilter entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "badWords", "threshold");

	}

	@Override
	public SpamFilter findOne(final Request<SpamFilter> request) {
		assert request != null;

		SpamFilter result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneSpamFilterById(id);

		return result;
	}

}
