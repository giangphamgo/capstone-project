package vn.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Giang Pham
 *
 */
@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements Serializable{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;
	private String email;
	private String password;
	private String avatar;
	@Temporal(TemporalType.DATE)
	private Date registerDate;
	private Boolean status;
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> role = new HashSet<Role>();


}
