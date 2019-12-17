
package acme.features.authenticated.thread;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedThreadShowService implements AbstractShowService<Authenticated, Thread> {

	@Autowired
	private AuthenticatedThreadRepository repository;

	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		int idThread = request.getModel().getInteger("id");
		Thread thread = this.repository.findOneById(idThread);
		List<Authenticated> users = (List<Authenticated>) thread.getUsers();
		int idUser = request.getPrincipal().getAccountId();

		return users.stream().filter(x -> x.getUserAccount().getId() == idUser).count() > 0;
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<String> usersCollection = this.repository.findUsers(request.getModel().getInteger("id"));
		String users = "";
		for (String s : usersCollection) {
			users = users + ", " + s;
		}
		Collection<Authenticated> usuariosAnyadidos = this.repository.findAuthenticated(request.getModel().getInteger("id"));
		Collection<Authenticated> usuariosTotales = this.repository.findAllAuthenticated();
		Collection<Authenticated> usuariosNoAnyadidos = usuariosTotales.stream().filter(x->!usuariosAnyadidos.contains(x)).collect(Collectors.toList());
		model.setAttribute("usuariosNoAnyadidos", usuariosNoAnyadidos);
		model.setAttribute("usertoadd", "");
		model.setAttribute("usuariosAnyadidos", usuariosAnyadidos);
		model.setAttribute("usertodelete", "");

		int idThread = request.getModel().getInteger("id");
		model.setAttribute("id", idThread);

		request.unbind(entity, model, "title", "moment");
		model.setAttribute("users", users.substring(2));
	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		assert request != null;

		Thread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
