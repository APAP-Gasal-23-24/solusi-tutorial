package apap.tutorial.bacabaca.controller;

import apap.tutorial.bacabaca.dto.PenulisMapper;
import apap.tutorial.bacabaca.dto.request.CreatePenulisRequestDTO;
import apap.tutorial.bacabaca.dto.request.DeleteMultiplePenulisDTO;
import apap.tutorial.bacabaca.model.Sertifikasi;
import apap.tutorial.bacabaca.service.PenulisService;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PenulisController {

    @Autowired
    PenulisService penulisService;

    @Autowired
    PenulisMapper penulisMapper;

    @GetMapping("penulis/create")
    public String formAddPenulis(Model model) {

        // Membuat DTO untuk dikirimkan ke view.
        var penulisDTO = new CreatePenulisRequestDTO();

        model.addAttribute("penulisDTO", penulisDTO);

        return "form-create-penulis";
    }

    @GetMapping("penulis/viewall")
    public String listPenulis(Model model) {
        var listPenulis = penulisService.getAllPenulis();
        var deleteDTO = new DeleteMultiplePenulisDTO();

        model.addAttribute("listPenulis", listPenulis);
        model.addAttribute("deleteDTO", deleteDTO);

        return "viewall-penulis";
    }

    @PostMapping("penulis/delete")
    public String deleteMultiplePenulis(
            @ModelAttribute DeleteMultiplePenulisDTO deleteDTO
    ) {
        penulisService.deleteListPenulis(deleteDTO.getListPenulis());
        return "success-delete-penulis";
    }

    @PostMapping(value = "penulis/create", params = {"addRow"})
    public String addRowSertifikasiPenulis(
            @ModelAttribute CreatePenulisRequestDTO createPenulisRequestDTO,
            Model model
    ) {
        if (createPenulisRequestDTO.getListSertifikasi() == null || createPenulisRequestDTO.getListSertifikasi().size() == 0) {
            createPenulisRequestDTO.setListSertifikasi(new ArrayList<>());
        }

        // Memasukkan Sertifikasi baru (kosong) ke list, untuk dirender sebagai row baru.
        createPenulisRequestDTO.getListSertifikasi().add(new Sertifikasi());

        model.addAttribute("penulisDTO", createPenulisRequestDTO);
        return "form-create-penulis";
    }

    @PostMapping(value = "penulis/create", params = {"deleteRow"})
    public String deleteRowSertifikasiPenulis(
            @ModelAttribute CreatePenulisRequestDTO createPenulisRequestDTO,
            @RequestParam("deleteRow") int row,
            Model model
    ) {
        createPenulisRequestDTO.getListSertifikasi().remove(row);
        model.addAttribute("penulisDTO", createPenulisRequestDTO);
        return "form-create-penulis";
    }

    @PostMapping("penulis/create")
    public String addPenulis(@Valid @ModelAttribute CreatePenulisRequestDTO penulisDTO, BindingResult bindingResult, Model model) {

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


        var penulis = penulisMapper.createPenulisRequestDTOToPenulis(penulisDTO);

        penulisService.createPenulis(penulis);

        model.addAttribute("penulis", penulisDTO);

        return "success-create-penulis";
    }
}
