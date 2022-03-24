package cl.desafio.sistema.imbd.main.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ratings")
public class Rating {
	
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_RATINGS")
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATINGS_GENERATOR")
	@SequenceGenerator(name = "RATINGS_GENERATOR", sequenceName = "SEQ_RATINGS", allocationSize = 1)
	@Column(name="rating_id", nullable=false, unique=true)
	private Long id;
	
	@Max(5)
	@Min(1)
	@Column(name="rating", nullable=false)
	private int rating;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToMany(mappedBy = "ratings")
	private List<Show> shows;
	
}
