package apap.tutorial.bacabaca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sertifikasi")
public class Sertifikasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSertifikasi;

    @NotNull
    @Size(max = 240)
    @Column(name = "nama_sertifikasi", nullable = false)
    private String namaSertifikasi;

    @NotNull
    @Size(max = 240)
    @Column(name = "penerbit_sertifikasi", nullable = false)
    private String penerbitSertifikasi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_penulis", referencedColumnName = "idPenulis")
    private Penulis penulis;
}