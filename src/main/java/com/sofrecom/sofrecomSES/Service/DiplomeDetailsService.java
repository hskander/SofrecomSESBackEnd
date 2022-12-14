package com.sofrecom.sofrecomSES.Service;

import com.sofrecom.sofrecomSES.Exeption.UserNotFoundException;
import com.sofrecom.sofrecomSES.Model.CentreFormation;
import com.sofrecom.sofrecomSES.Model.Diplome;
import com.sofrecom.sofrecomSES.Model.DiplomeDetails;
import com.sofrecom.sofrecomSES.Model.Institut;
import com.sofrecom.sofrecomSES.Repository.DiplomeDetailsRepository;
import com.sofrecom.sofrecomSES.Repository.DiplomeRepository;
import com.sofrecom.sofrecomSES.Repository.InstitutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class DiplomeDetailsService implements DiplomeDetailsServiceInterface {
    private final DiplomeDetailsRepository diplomeDetailsRepository;
    private final DiplomeRepository diplomeRepository;
    private final InstitutRepository institutRepository;
    @Autowired
    public DiplomeDetailsService(DiplomeDetailsRepository diplomeDetailsRepository,
                                 DiplomeRepository diplomeRepository,InstitutRepository institutRepository) {
        this.diplomeDetailsRepository = diplomeDetailsRepository;
        this.diplomeRepository=diplomeRepository;
        this.institutRepository=institutRepository;
    }

    public DiplomeDetails addDiplomeDetails(DiplomeDetails diplomeDetails, Long idDiplome,Long idInstitut){
        Diplome diplome=this.diplomeRepository.findDiplomeById(idDiplome).
                orElseThrow(()->new UserNotFoundException("User with ID "+idDiplome+" was not found" ));

        Institut institut = this.institutRepository.findInstitutById(idInstitut).
                orElseThrow(()->new UserNotFoundException("User with ID "+idInstitut+" was not found" ));

        diplomeDetails.setDiplome(diplome);
        diplomeDetails.setInstitut(institut);
        return this.diplomeDetailsRepository.save(diplomeDetails);
    }
}
