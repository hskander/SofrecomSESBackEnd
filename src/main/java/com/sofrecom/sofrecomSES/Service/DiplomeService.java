package com.sofrecom.sofrecomSES.Service;

import com.sofrecom.sofrecomSES.Exeption.UserNotFoundException;
import com.sofrecom.sofrecomSES.Model.Diplome;
import com.sofrecom.sofrecomSES.Model.Institut;
import com.sofrecom.sofrecomSES.Repository.DiplomeRepository;
import com.sofrecom.sofrecomSES.Repository.InstitutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiplomeService implements DiplomeServiceInterface {
    private final DiplomeRepository diplomeRepository;
    private final InstitutRepository institutRepository;
    @Autowired
    public DiplomeService(DiplomeRepository diplomeRepository,InstitutRepository institutRepository) {
        this.diplomeRepository = diplomeRepository;
        this.institutRepository = institutRepository;
    }

    public Diplome addDiplome(Diplome diplome){
        return this.diplomeRepository.save(diplome);
    }
}
