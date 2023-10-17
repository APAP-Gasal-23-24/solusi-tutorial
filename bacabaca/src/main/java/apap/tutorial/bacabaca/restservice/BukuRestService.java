package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Sertifikasi;
import apap.tutorial.bacabaca.rest.BukuDetail;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface BukuRestService {
    void createRestBuku(Buku buku);
    List<Buku> retrieveRestAllBuku();
    Buku getRestBukuById(UUID id);
    void updateRestBuku(Buku buku);
    Mono<String> getStatus(String code);
    Mono<BukuDetail> postStatus();
    List<Buku> getBukuByHurufAwal(String prefix);
    List<Sertifikasi> getBukuSerifikasi(UUID idBuku);
}
