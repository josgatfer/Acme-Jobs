
package acme.features.administrator.spamFilter;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamFilters.SpamFilter;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorSpamFilterListService implements AbstractListService<Administrator, SpamFilter> {

	@Autowired
	AdministratorSpamFilterRepository repository;


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
	public Collection<SpamFilter> findMany(final Request<SpamFilter> request) {
		assert request != null;

		Collection<SpamFilter> result;

		result = this.repository.findManyAll();

		return result;
	}

}
