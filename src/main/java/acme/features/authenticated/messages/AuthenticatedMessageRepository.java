
package acme.features.authenticated.messages;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Messages;
import acme.entities.spamFilters.SpamFilter;
import acme.entities.threads.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Messages m where m.id = ?1")
	Messages findOneById(int id);

	@Query("select t from Thread t where t.id = ?1")
	Thread findThreadById(int id);

	@Query("select t from Thread t join t.messages m where m.id = ?1")
	Thread findThreadByMessageId(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneAuthenticatedByUserAccountId(int id);
	
	@Query("select s from SpamFilter s")
	SpamFilter findSpamFilter();
}
