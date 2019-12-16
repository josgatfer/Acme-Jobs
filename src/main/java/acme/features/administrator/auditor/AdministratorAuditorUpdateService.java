/*
 * AdministratorAuditorUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorUpdateService implements AbstractUpdateService<Administrator, Auditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorAuditorRepository repository;

	// AbstractUpdateService<Administrator, Auditor> interface -----------------


	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void bind(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibilityStatement", "status");
	}

	@Override
	public Auditor findOne(final Request<Auditor> request) {
		assert request != null;

		Auditor result;
		int auditorId;

		auditorId = request.getModel().getInteger("id");
		result = this.repository.findOneAuditorById(auditorId);

		return result;
	}

	@Override
	public void update(final Request<Auditor> request, final Auditor entity) {
		assert request != null;
		assert entity != null;

		if (request.getModel().getBoolean("status").equals(true)) {
			entity.setAccepted(true);
		} else {
			entity.setAccepted(false);
		}

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Auditor> request, final Response<Auditor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
