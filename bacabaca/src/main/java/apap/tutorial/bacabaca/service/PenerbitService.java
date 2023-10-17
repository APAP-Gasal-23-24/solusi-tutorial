package apap.tutorial.bacabaca.service;


import apap.tutorial.bacabaca.model.Penerbit;

import java.util.List;
import java.util.Map;

public interface PenerbitService {
    Penerbit createPenerbit(Penerbit penerbit);
    List<Penerbit> getAllPenerbit();

    Penerbit getPenerbitById(Long idPenerbit);

    Map<String, Integer> getPublisherBookCounts();
}
