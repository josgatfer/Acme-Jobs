
package acme.features.authenticated.thread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.threads.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedThreadRepository extends AbstractRepository {

	//Comprobando el formato de la tabla intermedia generada
	//	@Query("select t from Thread t where t.id in(select a.thread.id from Participations a where a.user.id= ?1)")
	@Query("select distinct t from Thread t join t.users u on u.id=?1")
	Collection<Thread> findManyByUserId(int id);

	@Query("select t from Thread t where t.id = ?1")
	Thread findOneById(int id);

	@Query("select a.userAccount.username from Thread t join t.users a where t.id = ?1")
	Collection<String> findUsers(int id);
	
	@Query("select a from Thread t join t.users a where t.id = ?1")
	Collection<Authenticated> findAuthenticated(int id);
	
	@Query("select a from Authenticated a")
	Collection<Authenticated> findAllAuthenticated();
	
	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findUser(int id);

}
