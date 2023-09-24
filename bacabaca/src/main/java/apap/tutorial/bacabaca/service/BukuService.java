package apap.tutorial.bacabaca.service;

import apap.tutorial.bacabaca.dto.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.dto.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.model.Buku;

import java.util.List;
import java.util.UUID;

public interface BukuService {
    //Method untuk menambahkan buku
    void saveBuku(Buku buku);

    //Method untuk mendapatkan buku yang telah tersimpan
    List<Buku> getAllBuku();

    //Method untuk mendapatkan data buku berdasarkan kode buku
    Buku getBukuById(UUID id);

    boolean isJudulExist(String judul);

    boolean isJudulExist(String judul, UUID id);

    Buku updateBuku(Buku buku);

    void deleteBuku(Buku buku);

    List<Buku> listBukuFiltered(String judul);
}
