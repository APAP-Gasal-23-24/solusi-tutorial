package apap.tutorial.bacabaca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "penulis")
public class Penulis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPenulis;

    @NotNull
    @Size(max = 30)
    @Column(name = "nama_penulis", nullable = false)
    private String namaPenulis;

    @NotNull
    @Column(name = "biografi", nullable = false)
    private String biografi;

    @ManyToMany(mappedBy = "listPenulis", fetch = FetchType.LAZY)
    List<Buku> listBuku;
}
