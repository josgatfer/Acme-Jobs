
package acme.features.administrator.spamFilter;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spamFilters.SpamFilter;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamFilterRepository extends AbstractRepository {

	@Query("select a from SpamFilter a where a.id = ?1")
	SpamFilter findOneSpamFilterById(int id);

	@Query("select a from SpamFilter a")
	Collection<SpamFilter> findManyAll();
}
