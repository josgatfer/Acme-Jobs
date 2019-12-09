
package acme.entities.companies;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Company extends DomainEntity {

	private static final long	serialVersionUID	= 7L;

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	@URL
	private String				web;

	@NotBlank
	private String				ceo;

	@NotBlank
	private String				activities;

	@NotBlank
	@Pattern(regexp = "^[+][1-9]\\d{0,2}\\s[(]\\d{1,4}[)]\\s\\d{6,10}$")
	private String				phone;

	@NotBlank
	@Email
	private String				email;

	@Min(value = 0)
	@Max(value = 5)
	private Integer				stars;

	//Derived attribute
	
	@NotNull
	private Boolean incorporated;
	
}
