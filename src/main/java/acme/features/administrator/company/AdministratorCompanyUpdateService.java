
package acme.features.administrator.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companies.Company;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCompanyUpdateService implements AbstractUpdateService<Administrator, Company> {

	@Autowired
	private AdministratorCompanyRepository repository;

	@Override
	public boolean authorise(Request<Company> request) {
		assert request!=null;
		return true;
	}

	@Override
	public void bind(Request<Company> request, Company entity, Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;	
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(Request<Company> request, Company entity, Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "name", "sector", "email", "phone", "activities", "stars", "incorporated", "web", "ceo");
	}

	@Override
	public Company findOne(Request<Company> request) {
		assert request!=null;
		
		Company result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(Request<Company> request, Company entity, Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		
	}

	@Override
	public void update(Request<Company> request, Company entity) {
		assert request!=null;
		assert entity!=null;
		
		this.repository.save(entity);
		
	}


	
}
