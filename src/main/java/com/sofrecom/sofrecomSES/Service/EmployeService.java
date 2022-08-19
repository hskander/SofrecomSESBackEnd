package com.sofrecom.sofrecomSES.Service;

import com.sofrecom.sofrecomSES.Exeption.UserNotFoundException;
import com.sofrecom.sofrecomSES.Model.*;
import com.sofrecom.sofrecomSES.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeService implements EmployeServiceInterface {
    //Repositories
    private final EmployeeRepository employeeRepo;
    private final PoleRepository poleRepo;
    private final PosteRepository posteRepo;

    //Services

   private final ExperienceServiceInterface experienceService;
    private final DiplomeDetailsServiceInterface diplomeDetailsService;
    private final CertificatDetailsServiceInterface certificatDetailsService;
    private final CentreFormationRepository centreFormationRepository;
    private final CertificatRepositry certificatRepositry;
    private final InstitutRepository institutRepository;
    private final DiplomeRepository diplomeRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final ExperienceRepository experienceRepository;
    private final DiplomeDetailsRepository diplomeDetailsRepository;
    private final CertificatDetailsRepository certificatDetailsRepository;

    @Autowired
    public EmployeService(EmployeeRepository employeeRepo,PoleRepository poleRepo,PosteRepository posteRepo,
                          ExperienceServiceInterface experienceService, DiplomeDetailsServiceInterface diplomeDetailsService, CertificatDetailsServiceInterface certificatDetailsService
                          ,CentreFormationRepository centreFormationRepository, InstitutRepository institutRepository,
                          EntrepriseRepository entrepriseRepository,ExperienceRepository experienceRepository,
                          DiplomeDetailsRepository diplomeDetailsRepository,CertificatDetailsRepository certificatDetailsRepository,
                          DiplomeRepository diplomeRepository, CertificatRepositry certificatRepositry) {
        this.employeeRepo = employeeRepo;
        this.poleRepo=poleRepo;
        this.posteRepo=posteRepo;
        this.experienceService=experienceService;
        this.certificatDetailsService=certificatDetailsService;
        this.diplomeDetailsService=diplomeDetailsService;
        this.centreFormationRepository=centreFormationRepository;
        this.certificatRepositry=certificatRepositry;
        this.institutRepository=institutRepository;
        this.diplomeRepository=diplomeRepository;
        this.entrepriseRepository=entrepriseRepository;
        this.experienceRepository=experienceRepository;
        this.diplomeDetailsRepository=diplomeDetailsRepository;
        this.certificatDetailsRepository=certificatDetailsRepository;
    }
    @Override
    public Employe addEmployee(Employe employe, Long posteId, Long poleId){

        Pole pole = this.poleRepo.findPoleById(poleId).
                orElseThrow(()->new UserNotFoundException("Pole with ID "+poleId+" was not found" ));
        Poste poste= this.posteRepo.findPosteById(posteId).
                orElseThrow(()->new UserNotFoundException("Poste with ID "+posteId+" was not found" ));
        employe.setPole(pole);
        employe.setPoste(poste);
        employe.setEmployeCode(UUID.randomUUID().toString());

        return this.employeeRepo.save(employe);
    }
    @Override
    public Employe AffecterDiplomeEmployee(DiplomeDetails diplomeDetails,Long employeId,Long diplomeId, Long institutId){
        this.diplomeDetailsService.addDiplomeDetails(diplomeDetails,diplomeId,institutId,employeId);
        return this.employeeRepo.findEmployeById(employeId).
                orElseThrow(()->new UserNotFoundException("Employe with ID "+employeId+" was not found" ));
    }
    @Override
    public Employe AffecterCertificatEmployee(CertificatDetails certificatDetails,Long employeId,Long certificatId,Long centreFormationId){
        this.certificatDetailsService.addCertificatDetails(certificatDetails,certificatId,centreFormationId,employeId);
        return this.employeeRepo.findEmployeById(employeId).
                orElseThrow(()->new UserNotFoundException("Employe with ID "+employeId+" was not found" ));

    }
    @Override
    public Employe AffecterExperienceEmployee(Experience experience,Long employeId,Long entrepriseId){

       this.experienceService.addExperience(experience,entrepriseId,employeId);
       return this.employeeRepo.findEmployeById(employeId).
                orElseThrow(()->new UserNotFoundException("Employe with ID "+employeId+" was not found" ));

    }
    @Override
    public List<Employe> getAllEmployees(){
        return this.employeeRepo.findAll();
    }
    @Override
    public Employe getEmployeById(Long id){
        return this.employeeRepo.findEmployeById(id)
                .orElseThrow(()->new UserNotFoundException("Employe with ID "+id+" was not found" ));
    }
    @Override
    public Employe updateEmployee(Employe e){
        return this.employeeRepo.save(e);
    }
    @Override
    public void deleteEmployee(Long id){
        this.employeeRepo.deleteEmployeById(id);
    }
    @Override
    public Employe findEmployePoleManager(Long emlpoyeId){
        Employe employe =this.employeeRepo.findEmployeById(emlpoyeId)
                .orElseThrow(()->new UserNotFoundException("Employe with ID "+emlpoyeId+" was not found" ));
        return employe.getPole().getManager();
    }
    @Override
    public Employe findEmployeDirectionManager(Long emlpoyeId){
        Employe employe =this.employeeRepo.findEmployeById(emlpoyeId)
                .orElseThrow(()->new UserNotFoundException("Employe with ID "+emlpoyeId+" was not found" ));
        return employe.getPole().getDirection().getManager();
    }

    @Override
    public List<Employe> findEmployeesByNomPrenomEmail(String nomPrenom) {
        return this.employeeRepo.findEmployeesByNomPrenomEmail(nomPrenom);
    }

    @Override
    public List<Employe> findEmployeesByPoste(String poste) {
        return this.employeeRepo.findEmployeesByPoste(poste);
    }

    @Override
    public List<Employe> findEmployeesByCentreFormation(Long centreFormationId) {
        CentreFormation centreFormation=this.centreFormationRepository.findCentreFormationById(centreFormationId).
                orElseThrow(()->new UserNotFoundException("CentreFormation with ID "+centreFormationId+" was not found" ));
        return this.certificatDetailsRepository.findEmployeesByCentreFormation(centreFormation);

    }
    @Override
    public List<Employe> findEmployeesByCertificat(Long certificatId) {
        Certificat certificat=this.certificatRepositry.findCertificatById(certificatId).
                orElseThrow(()->new UserNotFoundException("certificat with ID "+certificatId+" was not found" ));
        return this.certificatDetailsRepository.findEmployeesByCertificat(certificat);
    }

    @Override
    public List<Employe> findEmployeesByInstitut(Long institutId) {
        Institut institut=this.institutRepository.findInstitutById(institutId).
                orElseThrow(()->new UserNotFoundException("institut with ID "+institutId+" was not found" ));
        return this.diplomeDetailsRepository.findEmployeesByInstitut(institut);
    }
    @Override
    public List<Employe> findEmployeesByDiplome(Long diplomeId) {
        Diplome diplome=this.diplomeRepository.findDiplomeById(diplomeId).
                orElseThrow(()->new UserNotFoundException("diplome with ID "+diplomeId+" was not found" ));
        return this.diplomeDetailsRepository.findEmployeesByDiplome(diplome);
    }

    @Override
    public List<Employe> findEmployeesByEntreprise(Long entrepriseId) {
        Entreprise entreprise=this.entrepriseRepository.findEntrepriseById(entrepriseId).
                orElseThrow(()->new UserNotFoundException("entreprise with ID "+entrepriseId+" was not found" ));
        return this.experienceRepository.findEmployeesByEntreprise(entreprise);
    }


}
