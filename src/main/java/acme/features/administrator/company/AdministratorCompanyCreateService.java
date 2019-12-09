
package acme.features.administrator.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companies.Company;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCompanyCreateService implements AbstractCreateService<Administrator, Company> {

	@Autowired
	AdministratorCompanyRepository repository;
	
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
	public Company instantiate(Request<Company> request) {
		Company result;
		
		result = new Company();
		
		result.setIncorporated(false);
		
		return result;
	}

	@Override
	public void validate(Request<Company> request, Company entity, Errors errors) {
		assert request !=null;
		assert entity!=null;
		assert errors!=null;
		
	}

	@Override
	public void create(Request<Company> request, Company entity) {
		this.repository.save(entity);
		
	}

	

}
