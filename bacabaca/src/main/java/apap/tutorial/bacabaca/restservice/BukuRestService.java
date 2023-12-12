package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.rest.BukuDetail;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BukuRestService {
    void createRestBuku(Buku buku);

    List<Buku> retrieveRestAllBuku();

    Buku getRestBukuById(UUID id);

    Mono<String> getStatus();

    Mono<BukuDetail> postStatus();

    Buku translateJudul(String idBuku, String sourceLanguage, String targetLanguage);

    Map<String, Double> chartBuku();
    
}
