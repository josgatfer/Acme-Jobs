
package acme.features.authenticated.thread;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedThreadUpdateAddUserService implements AbstractUpdateService<Authenticated, Thread> {

	@Autowired
	AuthenticatedThreadRepository repository;

	@Override
	public boolean authorise(Request<Thread> request) {
		assert request!=null;
		
		Collection<Authenticated> usuarios = this.repository.findAuthenticated(request.getModel().getInteger("id"));
		List<Integer> ids = usuarios.stream().map(x->x.getId()).collect(Collectors.toList());
		return ids.contains(request.getPrincipal().getActiveRoleId());
	}

	@Override
	public void bind(Request<Thread> request, Thread entity, Errors errors) {
		assert request !=null;
		assert entity!=null;
		assert errors!=null;
		
		int idUsuario = request.getModel().getInteger("usertoadd");
		Collection<Authenticated> authenticateds = this.repository.findAuthenticated(entity.getId());
		authenticateds.add(this.repository.findUser(idUsuario));
		entity.setUsers(authenticateds);
		request.bind(entity, errors, "title", "moment", "messages", "users");
		
	}

	@Override
	public void unbind(Request<Thread> request, Thread entity, Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model);
	}

	@Override
	public Thread findOne(Request<Thread> request) {
		assert request!=null;
		int id = request.getModel().getInteger("id");
		return this.repository.findOneById(id);
	}

	@Override
	public void validate(Request<Thread> request, Thread entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(Request<Thread> request, Thread entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);		
	}

	

}
