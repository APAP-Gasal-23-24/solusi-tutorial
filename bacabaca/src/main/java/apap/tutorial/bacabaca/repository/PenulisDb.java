package apap.tutorial.bacabaca.repository;

import apap.tutorial.bacabaca.model.Penulis;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PenulisDb extends JpaRepository<Penulis, Long> {

    Integer deleteByIdPenulisIn(List<Long> listIdPenulis);
}
