
package acme.features.consumer.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "text", "ticker", "deadline", "moneyMin", "moneyMax");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;
		result = new Offer();
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String currencyMin;
		int lengthMin = request.getModel().getString("moneyMin").length();
		String currencyMax;
		int lengthMax = request.getModel().getString("moneyMax").length();

		if (lengthMin == 0 || lengthMax == 0) {
			errors.state(request, lengthMin > 0, "moneyMin", "authenticated.offer.error.money-not-blank");
			errors.state(request, lengthMax > 0, "moneyMax", "authenticated.offer.error.money-not-blank");
		}
		currencyMin = request.getModel().getString("moneyMin");
		errors.state(request, currencyMin.contains("€") || currencyMin.contains("EUR"), "moneyMin", "authenticated.offer.error.eur");

		currencyMax = request.getModel().getString("moneyMax");
		errors.state(request, currencyMax.contains("€") || currencyMax.contains("EUR"), "moneyMax", "authenticated.offer.error.eur");

		boolean isAccepted;
		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.offer.error.must-accept");

	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
