
package acme.features.authenticated.auditorRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorRequest.AuditorRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRequestRepository extends AbstractRepository {

	@Query("select a from AuditorRequest a where a.id = ?1")
	AuditorRequest findOneById(int id);

	@Query("select a from AuditorRequest a")
	Collection<AuditorRequest> findManyAll();

	@Query("select count(a) from AuditorRequest a where a.accountId = ?1")
	Integer findAuditorRequestOfUser(int id);
}
