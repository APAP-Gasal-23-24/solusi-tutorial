package apap.tutorial.bacabaca.repository;

import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penerbit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PenerbitDb extends JpaRepository<Penerbit, Long> {
    List<Penerbit> findAll();
    Optional<Penerbit> findById(Long idPenerbit);

    List<Penerbit> findByNamaPenerbitStartsWith(String prefix);

    @Query("select p from Penerbit p order by p.namaPenerbit")
    List<Penerbit> sortPenerbitByNamaPenerbit();
}
