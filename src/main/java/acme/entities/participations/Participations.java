package acme.entities.participations;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import acme.entities.threads.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participations extends DomainEntity{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne(optional = false)
	private Thread thread;
	
	@NotNull
	@ManyToOne(optional = false)
	private Authenticated user;

}
