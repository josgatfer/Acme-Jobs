
package acme.features.authenticated.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequest.AuditorRequest;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorRequestCreateService implements AbstractCreateService<Authenticated, AuditorRequest> {

	@Autowired
	AuthenticatedAuditorRequestRepository repository;


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status");

	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibilityStatement");

		Integer alreadyRequested = this.repository.findAuditorRequestOfUser(request.getPrincipal().getAccountId());
		String alreadyReq;
		if (alreadyRequested == 1) {
			alreadyReq = "yes";
		} else {
			alreadyReq = "no";
		}
		model.setAttribute("alreadyReq", alreadyReq);
	}

	@Override
	public AuditorRequest instantiate(final Request<AuditorRequest> request) {

		AuditorRequest ar;
		ar = new AuditorRequest();
		ar.setAccountId(request.getPrincipal().getAccountId());

		return ar;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditorRequest> request, final AuditorRequest entity) {

		this.repository.save(entity);

	}

}
