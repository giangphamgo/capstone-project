package vn.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.shop.entities.User;

import javax.transaction.Transactional;

/**
 * @author Giang Pham
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM user WHERE email=?", nativeQuery = true)
	User findByEmail(String email);

	@Query(value = "SELECT COUNT(*) FROM user where DATEDIFF(CURDATE(), register_date)<=31", nativeQuery = true)
	Double countUserMonth();




	@Query(value = "SELECT COUNT(*) FROM user where DATEDIFF(CURDATE(), register_date)<=1", nativeQuery = true)
	Double countUserDay();

	@Query(value = "SELECT COUNT(user_id) FROM user ", nativeQuery = true)
	double countUserAll();
	@Modifying
	@Transactional
	@Query(value="INSERT INTO users_roles(user_id, role_id) values (?1, ?2)", nativeQuery=true)
	void addUserRole(long userId, long roleId);

}
