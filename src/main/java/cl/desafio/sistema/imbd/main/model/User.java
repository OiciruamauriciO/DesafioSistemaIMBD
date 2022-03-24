package cl.desafio.sistema.imbd.main.model;

import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.persistence.Transient;
import javax.validation.constraints.Size;

//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_USERS")
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_GENERATOR")
	@SequenceGenerator(name = "USERS_GENERATOR", sequenceName = "SEQ_USERS", allocationSize = 1)
	@Column(name="user_id", nullable=false, unique=true)
	private Long id;
	
	@Size(min=3, message="Usuario debe existir/estar presente")
	@Column(name="username", nullable=false)
	private String username;
	
	@Size(min=5, message="Correo debe ser mayor a cinco caracteres")
	@Column(name="email", nullable=false)
	private String email;
	
	@Size(min=8, message="Contraseña debe ser mayor a ocho caracteres")
	@Column(name="password", nullable=false)
	private String password;
	
	//@Transient
	@Column(name="passwordconfirmation")
	private String passwordconfirmation;

	@OneToMany(mappedBy = "creatorShow", fetch = FetchType.LAZY)
	private List<Show> userShows;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Rating> ratings;

	//@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
}
