package vn.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.shop.entities.Order;

import java.util.List;

/**
 * @author Giang Pham
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "select * from orders where user_id = ?1", nativeQuery = true)
	List<Order> findOrderByUserId(Long id);


	@Query(value = "delete from orders where order_id=?1", nativeQuery = true)
	void deletex(Long id);

	@Query(value = "Select COUNT(*) from products where category_id=4 and product_id= ?1", nativeQuery = true)
	int findOrderBycategoryId(Long id);

	@Query(value = "SELECT COUNT(*) FROM orders ", nativeQuery = true)
	int couldOderAll();

	@Query(value = "SELECT * FROM orders where status=0", nativeQuery = true)
	List<Order> fileOderCXN();

	@Query(value = "SELECT * FROM orders where status=1", nativeQuery = true)
	List<Order> fileOderCGH();

	@Query(value = "SELECT * FROM orders where status=2", nativeQuery = true)
	List<Order> fileOderDTT();

	@Query(value = "SELECT COUNT(*) FROM orders where DATEDIFF(CURDATE(), order_date)<=31", nativeQuery = true)
	int couldOderMonth();

	@Query(value = "SELECT COUNT(*) FROM orders where status=0 and DATEDIFF(CURDATE(), order_date)<=31", nativeQuery = true)
	int couldOderCXNMonth();

	@Query(value = "SELECT COUNT(*) FROM orders where status=1 and DATEDIFF(CURDATE(), order_date)<=31", nativeQuery = true)
	int couldOderDGHMonth();

	@Query(value = "SELECT COUNT(*) FROM orders where status=2 and DATEDIFF(CURDATE(), order_date)<=31", nativeQuery = true)
	int couldOderDTTMonth();

}
