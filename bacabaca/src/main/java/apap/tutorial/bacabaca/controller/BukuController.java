package apap.tutorial.bacabaca.controller;

import apap.tutorial.bacabaca.dto.BukuMapper;
import apap.tutorial.bacabaca.dto.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.dto.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.dto.response.ReadBukuResponseDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penulis;
import apap.tutorial.bacabaca.restservice.BukuRestService;
import apap.tutorial.bacabaca.service.BukuService;
import apap.tutorial.bacabaca.service.PenerbitService;
import apap.tutorial.bacabaca.service.PenulisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class BukuController {

    @Autowired
    private BukuMapper bukuMapper;

    @Autowired
    private BukuService bukuService;

    @Autowired
    private BukuRestService bukuRestService;

    @Autowired
    private PenerbitService penerbitService;

    @Autowired
    private PenulisService penulisService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("buku/create")
    public String formAddBuku(Model model) {
        //Membuat DTO baru sebagai isian form pengguna
        var bukuDTO = new CreateBukuRequestDTO();
        model.addAttribute("bukuDTO", bukuDTO);

        // Add variable list penerbit ke 'listPenerbit' untuk dirender di thymeleaf
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());

        // Add variable list penulis ke 'listPenulisExisting' untuk dirender di thymeleaf
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());

        return "form-create-buku";
    }

    @PostMapping(value = "buku/create", params = {"addRow"})
    public String addRowPenulisBuku(
            @ModelAttribute CreateBukuRequestDTO createBukuRequestDTO,
            Model model
    ) {
        if (createBukuRequestDTO.getListPenulis() == null || createBukuRequestDTO.getListPenulis().size() == 0) {
            createBukuRequestDTO.setListPenulis(new ArrayList<>());
        }

        // Memasukkan Penulis baru (kosong) ke list, untuk dirender sebagai row baru.
        createBukuRequestDTO.getListPenulis().add(new Penulis());

        // Kirim list penerbit penulis untuk menjadi pilihan pada dropdown.
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());

        model.addAttribute("bukuDTO", createBukuRequestDTO);
        return "form-create-buku";
    }

    @PostMapping(value = "buku/create", params = {"deleteRow"})
    public String deleteRowPenulisBuku(
            @ModelAttribute CreateBukuRequestDTO createBukuRequestDTO,
            @RequestParam("deleteRow") int row,
            Model model
    ) {
        createBukuRequestDTO.getListPenulis().remove(row);
        model.addAttribute("bukuDTO", createBukuRequestDTO);

        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        return "form-create-buku";
    }

    @PostMapping("buku/create")
    public String addBuku(@Valid @ModelAttribute CreateBukuRequestDTO bukuDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            FieldError fieldError = (FieldError) error;
                            return fieldError.getField() + ": " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.toList());

            model.addAttribute("errors", errors);
            return "error-viewall";
        }



        if (bukuService.isJudulExist(bukuDTO.getJudul())){
            var errorMessage = "Maaf, judul buku sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }

        var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);
        //Memanggil Service addBuku
        bukuService.saveBuku(buku);

        //Add variabel id buku ke 'id' untuk dirender di thymeleaf
        model.addAttribute("id", buku.getId());

        //Add variabel judul ke 'judul' untuk dirender di thymeleaf
        model.addAttribute("judul", buku.getJudul());

        return "success-create-buku";
    }

    @GetMapping("buku/viewall")
    public String listBuku(Model model) {
        //Mendapatkan semua buku
        List<Buku> listBuku = bukuService.getAllBuku();

        //Add variabel semua bukuModel ke "ListBuku" untuk dirender pada thymeleaf
        model.addAttribute("listBuku", listBuku);
        return "viewall-buku";
    }

    @GetMapping("buku/viewall-with-datatables")
    public String listBukuDataTables(Model model) {
        //Mendapatkan semua buku
        List<Buku> listBuku = bukuService.getAllBuku();

        //Add variabel semua bukuModel ke "ListBuku" untuk dirender pada thymeleaf
        model.addAttribute("listBuku", listBuku);
        return "viewall-buku-datatables";
    }

    @GetMapping("buku/{id}")
    public String detailBuku(@PathVariable("id") UUID id, Model model) {
        //Mendapatkan buku melalui kodeBuku
        var buku = bukuService.getBukuById(id);
        var readBukuDTO = bukuMapper.bukuToReadBukuDTO(buku);

        model.addAttribute("buku", readBukuDTO);
        return "view-buku";
    }

    @GetMapping("buku/{id}/update")
    public String formUpdateBuku(@PathVariable("id") UUID id, Model model) {

        //Mengambil buku dengan id tersebut
        var buku = bukuService.getBukuById(id);

        //Memindahkan data buku ke DTO untuk selanjutnya diubah di form penggun
        var bukuDTO = bukuMapper.bukuToUpdateBukuRequestDTO(buku);

        model.addAttribute("bukuDTO", bukuDTO);
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());

        return "form-update-buku";
    }

    @PostMapping(value = "buku/update", params = {"addRow"})
    public String addRowPenulisBukuUpdate(
            @ModelAttribute UpdateBukuRequestDTO bukuDTO,
            Model model
    ) {
        if (bukuDTO.getListPenulis() == null || bukuDTO.getListPenulis().size() == 0) {
            bukuDTO.setListPenulis(new ArrayList<>());
        }

        // Memasukkan Penulis baru (kosong) ke list, untuk dirender sebagai row baru.
        bukuDTO.getListPenulis().add(new Penulis());

        // Kirim list penerbit dan penulis untuk menjadi pilihan pada dropdown.
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());

        model.addAttribute("bukuDTO", bukuDTO);

        return "form-update-buku";
    }

    @PostMapping(value = "buku/update", params = {"deleteRow"})
    public String deleteRowPenulisBukuUpdate(
            @ModelAttribute UpdateBukuRequestDTO bukuDTO,
            @RequestParam("deleteRow") int row,
            Model model
    ) {
        bukuDTO.getListPenulis().remove(row);
        model.addAttribute("bukuDTO", bukuDTO);

        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        return "form-update-buku";
    }

    @PostMapping("buku/update")
    public String updateBuku(@Valid @ModelAttribute UpdateBukuRequestDTO bukuDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            FieldError fieldError = (FieldError) error;
                            return fieldError.getField() + ": " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.toList());

            model.addAttribute("errors", errors);
            return "error-viewall";
        }

        if (bukuService.isJudulExist(bukuDTO.getJudul(), bukuDTO.getId())){
            var errorMessage = "Maaf, judul buku sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }

        var bukuFromDto = bukuMapper.updateBukuRequestDTOToBuku(bukuDTO);

        //Memanggil Service addBuku
        var buku = bukuService.updateBuku(bukuFromDto);

        //Add variabel kode buku ke 'kode' untuk dirender di thymeleaf
        model.addAttribute("id", buku.getId());

        //Add variabel judul ke 'judul' untuk dirender di thymeleaf
        model.addAttribute("judul", buku.getJudul());

        return "success-update-buku";
    }

    @GetMapping("buku/{id}/delete")
    public String deleteBuku(@PathVariable("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);
        bukuService.deleteBuku(buku);

        model.addAttribute("id", id);

        return "success-delete-buku";
    }

    @GetMapping("/search")
    public String filteredByJudul(@RequestParam(value = "query") String judul, Model model){
        List<Buku> listBukuFiltered = bukuService.listBukuFiltered(judul);
        model.addAttribute("listBuku", listBukuFiltered);
        return "viewall-buku";
    }

    @GetMapping(value = "/buku/chart")
    public String chartBuku(Model model){

        var result = bukuRestService.chartBuku();
        model.addAttribute("result", result);

        return "view-buku-chart";
    }
}
