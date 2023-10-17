package apap.tutorial.bacabaca.restcontroller;


import java.util.*;


import apap.tutorial.bacabaca.dto.request.TranslateTitleBukuRequestDTO;
import apap.tutorial.bacabaca.dto.response.ReadBukuResponseDTO;
import apap.tutorial.bacabaca.dto.response.TranslateResponseDTO;
import apap.tutorial.bacabaca.model.Sertifikasi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import apap.tutorial.bacabaca.dto.BukuMapper;
import apap.tutorial.bacabaca.dto.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.rest.BukuDetail;
import apap.tutorial.bacabaca.restservice.BukuRestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import apap.tutorial.bacabaca.model.Buku;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/api")
public class BukuRestController {

    @Autowired
    private BukuMapper bukuMapper;

    @Autowired
    private BukuRestService bukuRestService;

    @Value("${X-RapidAPI-Key}")
    private String apiKey;

    @Value("${X-RapidAPI-Host}")
    private String apiHost;


    @PostMapping(value = "/buku/translate")
    private Buku translateBookTitle(@Valid @RequestBody TranslateTitleBukuRequestDTO translateDTO, BindingResult bindingResult){
        Buku buku = bukuRestService.getRestBukuById(translateDTO.getBookId());
        TranslateResponseDTO translatedTitle = getData(translateDTO.getSourceLanguage(), translateDTO.getTargetLanguage(), buku.getJudul());
        buku.setJudul(translatedTitle.getData().getTranslatedText());
        bukuRestService.updateRestBuku(buku);

        return buku;
    }

    private TranslateResponseDTO getData(String source_language, String target_language, String book_title){
        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Define your form parameters
        MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
        formParams.add("source_language", source_language);
        formParams.add("target_language",target_language);
        formParams.add("text", book_title);

        // Define your custom headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/x-www-form-urlencoded");
        headers.set("X-RapidAPI-Key", apiKey); // masukan key api
        headers.set("X-RapidAPI-Host", apiHost); // masukan host api

        // Create an HttpEntity with the custom headers
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(formParams, headers);

        ResponseEntity<TranslateResponseDTO> responseEntity = restTemplate.exchange(
                "https://text-translator2.p.rapidapi.com/translate",
                HttpMethod.POST,
                httpEntity,
                TranslateResponseDTO.class
        );
        TranslateResponseDTO responseBody = responseEntity.getBody();
        return responseBody;
    }

    @GetMapping(value = "/buku/{id}/status")
    private Mono<String> getStatus(@PathVariable("id") String id){
        return bukuRestService.getStatus(id);
    }

    @PostMapping(value = "/full")
    private Mono<BukuDetail> postStatus(){
        return bukuRestService.postStatus();
    }


    @GetMapping(value = "/buku/view-all")
    private List<Buku> retrieveAllBuku(){
        return bukuRestService.retrieveRestAllBuku();
    }

    @GetMapping(value = "/buku/{id}")
    private Buku retrieveBuku(@PathVariable("id") String id){
        try{
            return bukuRestService.getRestBukuById(UUID.fromString(id));
        } catch (NoSuchElementException e){
//            HttpRequest

            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id Buku " + id + " not found"
            );
        }
    }

    @PostMapping(value = "/buku")
    public void restAddBuku(@Valid @RequestBody CreateBukuRequestDTO bukuDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else{
            var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);
            bukuRestService.createRestBuku(buku);
        }
    }

    @GetMapping(value = "/buku/huruf-awal/{hurufAwal}")
    public List<Buku> getBukuRestWithPrefix(@PathVariable("hurufAwal") String prefix){
        return bukuRestService.getBukuByHurufAwal(prefix);
    }

    @GetMapping(value = "/buku/{idBuku}/list-sertifikasi")
    public List<Sertifikasi> getSertifikasiBuku(@PathVariable("idBuku") UUID idBuku){
        return bukuRestService.getBukuSerifikasi(idBuku);
    }

}
