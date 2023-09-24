package apap.tutorial.bacabaca.repository;

import apap.tutorial.bacabaca.model.Penulis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenulisDb extends JpaRepository<Penulis, Long> {
}
