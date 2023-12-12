package apap.tutorial.bacabaca.restcontroller;

import apap.tutorial.bacabaca.dto.PenerbitMapper;
import apap.tutorial.bacabaca.dto.request.CreatePenerbitRequestDTO;
import apap.tutorial.bacabaca.dto.request.UpdatePenerbitRequestDTO;
import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.restservice.PenerbitRestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PenerbitRestController {

    @Autowired
    private PenerbitMapper penerbitMapper;
    @Autowired
    private PenerbitRestService penerbitRestService;

    @GetMapping(value = "/penerbit/view-all")
    private List<Penerbit> retrieveAllPenerbit(){return penerbitRestService.retrieveRestAllPenerbit();}

    @PostMapping(value = "/penerbit/create")
    private Penerbit createRestPenerbit(@Valid @RequestBody CreatePenerbitRequestDTO createPenerbitRequestDTO){
        Penerbit penerbit = penerbitMapper.createPenerbitRequestDTOToPenerbit(createPenerbitRequestDTO);
        return penerbitRestService.createRestPenerbit(penerbit);
    }

    @PutMapping(value = "/penerbit/{idPenerbit}")
    private Penerbit updateRestPenerbit(@Valid @RequestBody UpdatePenerbitRequestDTO updatePenerbitRequestDTO, @PathVariable("idPenerbit") String idPenerbit){
        updatePenerbitRequestDTO.setIdPenerbit(Long.parseLong(idPenerbit));
        Penerbit penerbit = penerbitMapper.updatePenerbitRequestDTOToPenerbit(updatePenerbitRequestDTO);
        return penerbitRestService.updateRestPenerbit(penerbit);
    }

    @GetMapping(value = "/penerbit/{idPenerbit}")
    private Penerbit retrivePenerbit(@PathVariable("idPenerbit") String idPenerbit){
        return penerbitRestService.getRestPenerbit(Long.parseLong(idPenerbit));
    }
    @DeleteMapping(value = "/penerbit/{idPenerbit}")
        private ResponseEntity<String> deletePenerbit(@PathVariable("idPenerbit") String idPenerbit){
        Penerbit penerbit = penerbitRestService.getRestPenerbit(Long.parseLong(idPenerbit));
        penerbitRestService.deteleRestPenerbit(penerbit);
        return new ResponseEntity<>("Penerbit has been deleted", HttpStatus.OK);
    }

}