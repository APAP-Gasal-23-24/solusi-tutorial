package apap.tutorial.bacabaca.restcontroller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import apap.tutorial.bacabaca.dto.BukuMapper;
import apap.tutorial.bacabaca.dto.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.dto.response.ReadBukuResponseDTO;
import apap.tutorial.bacabaca.rest.BukuDetail;
import apap.tutorial.bacabaca.restservice.BukuRestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping(value = "/buku/status")
    private Mono<String> getStatus(){
        return bukuRestService.getStatus();
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
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id Buku " + id + " not found"
            );
        }
    }

    @PostMapping(value = "/buku/create")
    public Buku restAddBuku(@Valid @RequestBody CreateBukuRequestDTO bukuDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else{
            var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);
            bukuRestService.createRestBuku(buku);
            return buku;
        }
    }

    @GetMapping(value = "/buku/translate")
    public ReadBukuResponseDTO translateJudulBuku(@RequestParam String idBuku, 
    @RequestParam String sourceLanguage, @RequestParam String targetLanguage){

        var bukuTranslated = bukuRestService.translateJudul(idBuku, sourceLanguage, targetLanguage);
        var bukuDTO = bukuMapper.bukuToReadBukuDTO(bukuTranslated);
        
        return bukuDTO;
    }
}
