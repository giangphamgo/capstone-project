package vn.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.shop.entities.Comment;

/**
 * @author Giang Pham
 *
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
