
package acme.features.authenticated.requestt;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Requestt;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestRepository extends AbstractRepository {

	@Query("select a from Requestt a where a.id = ?1")
	Requestt findOneById(int id);

	@Query("select a from Requestt a")
	Collection<Requestt> findManyAll();
}
