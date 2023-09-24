package apap.tutorial.bacabaca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "penerbit")
public class Penerbit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPenerbit;

    @NotNull
    @Size(max = 30)
    @Column(name = "nama_penerbit", nullable = false)
    private String namaPenerbit;

    @NotNull
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "penerbit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Buku> listBuku = new ArrayList<>();
}
