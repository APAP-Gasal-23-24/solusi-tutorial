package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.repository.PenerbitDb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PenerbitRestServiceImpl implements PenerbitRestService{
    @Autowired
    private PenerbitDb penerbitDb;

    @Override
    public List<Penerbit> retrieveRestAllPenerbit(){
       return penerbitDb.findAll();
    }

    @Override
    public Penerbit createRestPenerbit(Penerbit penerbit){
        return penerbitDb.save(penerbit);
    }

    @Override
    public Penerbit updateRestPenerbit(Penerbit penerbit){
        return penerbitDb.save(penerbit);
    }

    @Override
    public Penerbit getRestPenerbit(long penerbitId){
        for(Penerbit penerbit : retrieveRestAllPenerbit()){
            if(penerbit.getIdPenerbit() == penerbitId){
                return  penerbit;
            }
        }
        return null;
    }

    @Override
    public void deteleRestPenerbit(Penerbit penerbit){
         penerbitDb.delete(penerbit);
    };

    @Override
    public List<Penerbit> getPenerbitByHurufAwal(String prefix){
        List<Penerbit> penerbitWithPrefix = penerbitDb.findByNamaPenerbitStartsWith(prefix);
        return penerbitWithPrefix;
    }

    @Override
    public List<Penerbit> getPenerbitOrderedByNamaPenerbit(){
        List<Penerbit> penerbitOrdered = penerbitDb.sortPenerbitByNamaPenerbit();
        return penerbitOrdered;
    }
}
