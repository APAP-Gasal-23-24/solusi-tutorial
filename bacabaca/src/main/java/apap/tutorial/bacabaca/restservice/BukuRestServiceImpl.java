package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.dto.response.BukuApiDTO;
import apap.tutorial.bacabaca.dto.response.BukuApiResponseDTO;
import apap.tutorial.bacabaca.dto.response.TranslateDTO;
import apap.tutorial.bacabaca.model.Buku;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class BukuRestServiceImpl implements BukuRestService {

    @Autowired
    private BukuDb bukuDb;

    private final String mockUrl = System.getenv("MOCK_URL");

    private final WebClient webClient;

    public BukuRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(mockUrl).build(); // mock server
    }

    @Override
    public Mono<String> getStatus() {
        return this.webClient.get().uri("/rest/buku/status").retrieve().bodyToMono(String.class);
    };

    @Override
    public Mono<BukuDetail> postStatus() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("judul", "Buku Cara Membaca Jilid 2");
        data.add("tahunTerbit", "2003");
        var response = this.webClient
                .post()
                .uri("/rest/buku/full-status")
                .bodyValue(data)
                .retrieve()
                .bodyToMono(BukuDetail.class);

        return response;
    }

    @Override
    public Buku translateJudul(String idBuku, String sourceLanguage, String targetLanguage) {

        var newWebClient = WebClient.builder()
                .baseUrl("https://text-translator2.p.rapidapi.com/translate").build();

        var bukuOptional = bukuDb.findById(UUID.fromString(idBuku));
        if (!bukuOptional.isPresent()) {
            return null;
        }

        var buku = bukuOptional.get();
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("text", buku.getJudul());
        data.add("source_language", sourceLanguage);
        data.add("target_language", targetLanguage);

        var response = newWebClient
                .post()
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "a7bc6f5206msh410867102faf8d2p12f8cejsnfc55ccf9ecda")
                .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .bodyValue(data)
                .retrieve()
                .bodyToMono(TranslateDTO.class);

        var judulTranslated = response.block().getData().getTranslatedText();
        buku.setJudul(judulTranslated);
        return buku;
    };

    @Override
    public void createRestBuku(Buku buku) {
        bukuDb.save(buku);
    };

    @Override
    public List<Buku> retrieveRestAllBuku() {
        return bukuDb.sortBukuByJudulLower();
    };

    @Override
    public Buku getRestBukuById(UUID id) {
        for (Buku buku : retrieveRestAllBuku()) {
            if (buku.getId().equals(id)) {
                return buku;
            }
        }
        return null;
    }

    @Override
    public Map<String, Double> chartBuku() {
        var newWebClient = WebClient.builder()
                .baseUrl("https://hapi-books.p.rapidapi.com/month/2023/9").build();

        var response = newWebClient
                .get()
                .header("X-RapidAPI-Key", "a7bc6f5206msh410867102faf8d2p12f8cejsnfc55ccf9ecda")
                .header("X-RapidAPI-Host", "hapi-books.p.rapidapi.com")
                .retrieve()
                .bodyToMono(BukuApiResponseDTO.class);

        var bukuApiResponseDTO = response.block();
        var listBukuApiDTO = bukuApiResponseDTO.getData();

        Map<String, Double> result = new HashMap<>();
        listBukuApiDTO.forEach(b -> {
            var name = b.getName();
            var rating = b.getRating();

            result.put(name, rating);
        });

        return result;
    }
}
