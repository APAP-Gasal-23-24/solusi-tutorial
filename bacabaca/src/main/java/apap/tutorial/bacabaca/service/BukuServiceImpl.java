package apap.tutorial.bacabaca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import apap.tutorial.bacabaca.model.Buku;

@Service
public class BukuServiceImpl implements BukuService{
    private List<Buku> listBuku;

    public BukuServiceImpl() {
        listBuku = new ArrayList<>();
    }

    @Override
    public void createBuku(Buku buku) {
        listBuku.add(buku);
    }

    @Override
    public List<Buku> getAllBuku() {
        return listBuku;
    }

    @Override
    public Buku getBukuById(UUID kodeBuku) {
        for (Buku buku : listBuku) {
            if (buku.getId().equals(kodeBuku)) {
                return buku;
            }
        }
        return null;
    }

    @Override
    public Buku updateBuku(UUID id, Buku updatedBuku) {
        Buku buku = listBuku.stream()
            .filter(b -> b.getId().equals(id))
            .findAny()
            .orElse(null);
        if (buku != null){
            buku.setHarga(updatedBuku.getHarga());
            buku.setJudul(updatedBuku.getJudul());
            buku.setPenulis(updatedBuku.getPenulis());
            buku.setJudul(updatedBuku.getJudul());
        }
        return buku;
    }

    @Override
    public void deleteBuku(UUID id) {
        listBuku.removeIf(b -> b.getId().equals(id));
    }

    @Override
    public boolean isJudulExist(String judul) {
        return listBuku.stream().anyMatch(b -> b.getJudul().equals(judul));
    }

    @Override
    public boolean isJudulExist(String judul, UUID id) {
        return listBuku.stream().anyMatch(b -> b.getJudul().equals(judul) && !b.getId().equals(id));
    }
}

