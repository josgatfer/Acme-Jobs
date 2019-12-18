
package acme.features.employer.duty;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.entities.spamFilters.SpamFilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerDutyUpdateService implements AbstractUpdateService<Employer, Duty> {

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

		request.unbind(entity, model, "title", "description", "timePercentage");
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;
		Duty result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(id);
		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Si el Job ya está publicado, no puede actualizarse
		errors.state(request, !entity.getDescriptor().getJob().isFinalMode(), "title", "employer.job.isAlreadyPublished");

		//Spam
		SpamFilter spam = this.repository.findAllSpamFilters().stream().collect(Collectors.toList()).get(0);
		Stream<String> badWords = Stream.of(spam.getBadWords().split(","));

		String title = (String) request.getModel().getAttribute("title");
		Long countBadWordsInTitle = badWords.filter(x -> title.contains(x)).count();
		errors.state(request, countBadWordsInTitle < spam.getThreshold(), "status", "employer.duty.titleHasSpam");

		Stream<String> badWords2 = Stream.of(spam.getBadWords().split(","));
		String description = (String) request.getModel().getAttribute("description");
		Long countBadWordsInDescription = badWords2.filter(x -> description.contains(x)).count();
		errors.state(request, countBadWordsInDescription < spam.getThreshold(), "status", "employer.duty.descriptionHasSpam");
	}

	@Override
	public void update(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
