package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penulis;
import apap.tutorial.bacabaca.model.Sertifikasi;
import apap.tutorial.bacabaca.repository.BukuDb;
import apap.tutorial.bacabaca.rest.BukuDetail;
import apap.tutorial.bacabaca.rest.Setting;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BukuRestServiceImpl implements BukuRestService {
    @Autowired
    private BukuDb bukuDb;

    private final WebClient webClient;

    public BukuRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.bukuUrl).build(); // mock server
    }

    @Override
    public Mono<String> getStatus(String id){
        return this.webClient.get().uri("/rest/buku/" + id + "/status").retrieve().bodyToMono(String.class);
    };

    @Override
    public Mono<BukuDetail> postStatus(){
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("judul","Buku Cara Membaca Jilid 2");
        data.add("tahunTerbit", "2003");
        return this.webClient.post().uri("/rest/buku/full-status").syncBody(data).retrieve().bodyToMono(BukuDetail.class);

    };
    @Override
    public void createRestBuku(Buku buku){
         bukuDb.save(buku);
    };

    @Override
    public void updateRestBuku(Buku buku){
        bukuDb.save(buku);
    }

    @Override
    public List<Buku> retrieveRestAllBuku(){
        return bukuDb.sortBukuByJudulLower();
    };

    @Override
    public Buku getRestBukuById(UUID id){
        for (Buku buku : retrieveRestAllBuku()) {
            if (buku.getId().equals(id)) {
                return buku;
            }
        }
        return null;
    };

    @Override
    public List<Buku> getBukuByHurufAwal(String prefix){
        List<Buku> bukuWithPrefix = bukuDb.findByJudulStartsWith(prefix);
        return bukuWithPrefix;
    }

    @Override
    public List<Sertifikasi> getBukuSerifikasi(UUID idBuku){
        Buku buku = getRestBukuById(idBuku);
        List<Penulis> listPenulis = buku.getListPenulis();
        List<Sertifikasi> listSertifikasi = new ArrayList<>();
        for (Penulis penulis : listPenulis) {
            listSertifikasi.addAll(penulis.getListSertifikasi());
        }
        return listSertifikasi;
    }
}
