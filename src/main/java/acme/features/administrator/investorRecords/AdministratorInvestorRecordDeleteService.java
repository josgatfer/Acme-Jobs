package acme.features.administrator.investorRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorRecords.InvestorRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorInvestorRecordDeleteService implements AbstractDeleteService<Administrator, InvestorRecord> {

	// Internal state

	@Autowired
	AdministratorInvestorRecordRepository repository;

	// AbstractDeleteService<Administrator, InvestorRecord> interface

	@Override
	public boolean authorise(Request<InvestorRecord> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void bind(Request<InvestorRecord> request, InvestorRecord entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<InvestorRecord> request, InvestorRecord entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "investor", "sector", "investingStatement", "stars");
	}

	@Override
	public InvestorRecord findOne(Request<InvestorRecord> request) {
		// TODO Auto-generated method stub
		assert request != null;

		InvestorRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(Request<InvestorRecord> request, InvestorRecord entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(Request<InvestorRecord> request, InvestorRecord entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
