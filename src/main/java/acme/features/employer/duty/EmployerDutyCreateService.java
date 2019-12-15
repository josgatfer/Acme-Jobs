
package acme.features.employer.duty;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.entities.spamFilters.SpamFilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int descId = request.getModel().getInteger("id");
		model.setAttribute("descId", descId);

		request.unbind(entity, model, "title", "description", "timePercentage", "descriptor");

	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		assert request != null;

		Duty result;
		result = new Duty();

		Integer id = request.getModel().getInteger("id");
		Descriptor descriptor = this.repository.findOneDescriptorById(id);
		result.setDescriptor(descriptor);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Spam
		SpamFilter spam = this.repository.findAllSpamFilters().stream().collect(Collectors.toList()).get(0);
		Stream<String> badWords = Stream.of(spam.getBadWords().split(","));

		String title = (String) request.getModel().getAttribute("title");
		Long countBadWordsInTitle = badWords.filter(x -> title.contains(x)).count();
		errors.state(request, countBadWordsInTitle < spam.getThreshold(), "title", "employer.duty.titleHasSpam");

		Stream<String> badWords2 = Stream.of(spam.getBadWords().split(","));
		String description = (String) request.getModel().getAttribute("description");
		Long countBadWordsInDescription = badWords2.filter(x -> description.contains(x)).count();
		errors.state(request, countBadWordsInDescription < spam.getThreshold(), "description", "employer.duty.descriptionHasSpam");
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
