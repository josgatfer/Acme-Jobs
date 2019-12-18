
package acme.entities.threads;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import acme.entities.messages.Messages;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Thread extends DomainEntity {

	private static final long					serialVersionUID	= 43L;

	@NotBlank
	private String								title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date								moment;

	//Para mapear en base a los valores "thread" de mensajes
	@OneToMany(mappedBy = "thread")
	private Collection<@Valid Messages>			messages;

	@NotEmpty
	//Usamos ManyToMany, ya que no nos hará falta acceder a la tabla intermedia
	@ManyToMany
	private Collection<@Valid Authenticated>	users;

}
