package apap.tutorial.bacabaca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE penerbit SET is_deleted = true WHERE id_penerbit=?")
@Where(clause = "is_deleted=false")
@Table(name = "penerbit")
public class Penerbit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPenerbit;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama_penerbit", nullable = false)
    private String namaPenerbit;

    @NotNull
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "penerbit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Buku> listBuku = new ArrayList<>();
}