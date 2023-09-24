package apap.tutorial.bacabaca.repository;

import apap.tutorial.bacabaca.model.Buku;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface BukuDb extends JpaRepository<Buku, UUID> {
    List<Buku> findByJudulContainingIgnoreCase(String judul);
    @Query("SELECT b FROM Buku b ORDER BY b.judulLower")
    List<Buku> sortBukuByJudulLower();
}
