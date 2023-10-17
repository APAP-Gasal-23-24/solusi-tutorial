package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Penerbit;

import java.util.List;

public interface PenerbitRestService {
        List<Penerbit> retrieveRestAllPenerbit();

        Penerbit createRestPenerbit(Penerbit penerbit);

        Penerbit updateRestPenerbit(Penerbit penerbit);

        Penerbit getRestPenerbit(long penerbitId);

        void deteleRestPenerbit(Penerbit penerbit);

        List<Penerbit> getPenerbitByHurufAwal(String prefix);

        List<Penerbit> getPenerbitOrderedByNamaPenerbit();
}
