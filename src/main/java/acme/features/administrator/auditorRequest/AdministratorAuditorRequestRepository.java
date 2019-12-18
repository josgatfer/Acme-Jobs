/*
 * AuthenticatedEmployerRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.auditorRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorRequest.AuditorRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuditorRequestRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select e from AuditorRequest e where e.id = ?1")
	AuditorRequest findOneAuditorRequestById(int id);

	@Query("select e from AuditorRequest e")
	Collection<AuditorRequest> findManyAll();

	@Query("select e from AuditorRequest e where e.status is null")
	Collection<AuditorRequest> findPendingAuditorRequest();

}
