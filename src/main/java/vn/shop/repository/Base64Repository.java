package vn.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.shop.entities.Base64;

/**
 * @author Giang Pham
 *
 */
@Repository
public interface Base64Repository extends JpaRepository<Base64, Long> {
}
