package acme.features.authenticated.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Messages;
import acme.entities.threads.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedThreadCreateService implements AbstractCreateService<Authenticated, Thread>{

	@Autowired
	AuthenticatedThreadRepository repository;

	@Override
	public boolean authorise(Request<Thread> request) {
		assert request!=null;
		return true;
	}

	@Override
	public void bind(Request<Thread> request, Thread entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "moment", "users", "messages");
		
	}

	@Override
	public void unbind(Request<Thread> request, Thread entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title");
		
	}

	@Override
	public Thread instantiate(Request<Thread> request) {
		Thread result;
		result = new Thread();
		Authenticated user = this.repository.findUser(request.getPrincipal().getActiveRoleId());
		List<Authenticated> users = new ArrayList<>();
		users.add(user);
		result.setUsers(users);
		return result;
	}

	@Override
	public void validate(Request<Thread> request, Thread entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(Request<Thread> request, Thread entity) {
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		
		List<Messages> messages = new ArrayList<>();
		entity.setMessages(messages);
		this.repository.save(entity);
		
	}
}
