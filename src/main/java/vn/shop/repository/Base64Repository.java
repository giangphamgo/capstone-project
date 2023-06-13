package vn.fs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fs.entities.Base64;

@Repository
public interface Base64Repository extends JpaRepository<Base64, Long> {
}
