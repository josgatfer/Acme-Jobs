
package acme.features.employer.descriptor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.roles.Employer;
import acme.entities.spamFilters.SpamFilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerDescriptorUpdateService implements AbstractUpdateService<Employer, Descriptor> {

	@Autowired
	EmployerDescriptorRepository repository;


	@Override
	public boolean authorise(final Request<Descriptor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Descriptor> request, final Descriptor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Descriptor> request, final Descriptor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description");
	}

	@Override
	public Descriptor findOne(final Request<Descriptor> request) {
		assert request != null;

		Descriptor result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDescriptorById(id);
		return result;
	}

	@Override
	public void validate(final Request<Descriptor> request, final Descriptor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Si el Job ya está publicado, no puede actualizarse
		errors.state(request, !entity.getJob().isFinalMode(), "description", "employer.job.isAlreadyPublished");

		//Spam
		SpamFilter spam = this.repository.findAllSpamFilters().stream().collect(Collectors.toList()).get(0);
		Stream<String> badWords = Stream.of(spam.getBadWords().split(","));

		String description = (String) request.getModel().getAttribute("description");
		Long countBadWordsInDescription = badWords.filter(x -> description.contains(x)).count();
		errors.state(request, countBadWordsInDescription < spam.getThreshold(), "description", "employer.descriptor.descriptionHasSpam");
	}

	@Override
	public void update(final Request<Descriptor> request, final Descriptor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
