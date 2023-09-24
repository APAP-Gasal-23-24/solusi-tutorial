package apap.tutorial.bacabaca.service;


import apap.tutorial.bacabaca.model.Penerbit;

import java.util.List;

public interface PenerbitService {
    Penerbit createPenerbit(Penerbit penerbit);
    List<Penerbit> getAllPenerbit();

    Penerbit getPenerbitById(Long idPenerbit);
}
