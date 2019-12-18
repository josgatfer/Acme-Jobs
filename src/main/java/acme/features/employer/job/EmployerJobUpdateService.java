
package acme.features.employer.job;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.entities.spamFilters.SpamFilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "status");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Si el Job ya está publicado, no puede actualizarse
		errors.state(request, !entity.isFinalMode(), "status", "employer.job.isAlreadyPublished");

		//Spam
		SpamFilter spam = this.repository.findAllSpamFilters().stream().collect(Collectors.toList()).get(0);
		Stream<String> badWords = Stream.of(spam.getBadWords().split(","));

		String title = (String) request.getModel().getAttribute("title");
		Long countBadWordsInTitle = badWords.filter(x -> title.contains(x)).count();
		errors.state(request, countBadWordsInTitle < spam.getThreshold(), "title", "employer.job.titleHasSpam");

		//Si va a hacerse Published, comprobamos que los Duties suman el 100%
		if (request.getModel().getAttribute("status").equals("Published")) {
			Descriptor descriptor = this.repository.findOneDescriptorByJobId(entity.getId());
			Collection<Duty> duties = this.repository.findAllDutiesByDescriptor(descriptor.getId());

			//Duties
			Integer dutiesPercentage = duties.stream().map(x -> x.getTimePercentage()).collect(Collectors.summingInt(x -> x));
			errors.state(request, dutiesPercentage == 100, "status", "employer.job.dutiesNotSum100");
		}

		//Aseguramos que el status es Published o Draft
		Boolean correctStatus = request.getModel().getAttribute("status").equals("Published") || request.getModel().getAttribute("status").equals("Draft");
		errors.state(request, correctStatus, "status", "employer.job.statusIsNotCorrect");

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		if (request.getModel().getAttribute("status").equals("Published")) {
			entity.setFinalMode(true);
		}

		this.repository.save(entity);
	}

}
