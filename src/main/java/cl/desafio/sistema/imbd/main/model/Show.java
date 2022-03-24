package cl.desafio.sistema.imbd.main.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="shows")
public class Show {

	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHOWS")
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOWS_GENERATOR")
	@SequenceGenerator(name = "SHOWS_GENERATOR", sequenceName = "SEQ_SHOWS", allocationSize = 1)
	@Column(name="show_id", nullable=false, unique=true)
	private Long id;
	
	@Size(min=1, message="Titulo debe existir/estar presente")
	@Column(name="showtitle", nullable=false)
	private String showtitle;
	
	@Size(min=1, message="Red debe existir/estar presente")
	@Column(name="network", nullable=false)
	private String network;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User creatorShow;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "shows_ratings", joinColumns = @JoinColumn(name = "show_id"), inverseJoinColumns = @JoinColumn(name = "rating_id"))
	private List<Rating> ratings;
}
