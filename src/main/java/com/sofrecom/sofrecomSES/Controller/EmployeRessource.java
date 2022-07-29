package com.sofrecom.sofrecomSES.Controller;

import com.sofrecom.sofrecomSES.Model.*;
import com.sofrecom.sofrecomSES.Service.CertificatDetailsService;
import com.sofrecom.sofrecomSES.Service.CertificatDetailsServiceInterface;
import com.sofrecom.sofrecomSES.Service.DiplomeDetailsServiceInterface;
import com.sofrecom.sofrecomSES.Service.EmployeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employee")
public class EmployeRessource {
    private final EmployeServiceInterface employeeService;
    private final CertificatDetailsServiceInterface certificatDetailsService;

    private final DiplomeDetailsServiceInterface diplomeDetailService;
    @Autowired
    public EmployeRessource(EmployeServiceInterface employeeService, CertificatDetailsServiceInterface certificatDetailsService, DiplomeDetailsServiceInterface diplomeDetailService) {
        this.employeeService = employeeService;
        this.certificatDetailsService = certificatDetailsService;
        this.diplomeDetailService = diplomeDetailService;
    }

    @GetMapping("/allEmployees")
    public ResponseEntity<List<Employe>> getAllEmployees(){
        List<Employe> employees=this.employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<Employe> findEmployeeByMatricule(@RequestParam("id") Long id){
        Employe employee= this.employeeService.getEmployeById(id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<?> deleteEmployee(@RequestParam("id") Long id){
        this.employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   /* @PostMapping("/addEmployee")
    public ResponseEntity<Employe> addEmployee(@RequestBody Employe employe,@RequestParam("posteMatricule") Long posteMatricule,@RequestParam("poleMatricule") Long poleMatricule,
                                               List<Experience> experiences, List<Diplome> diplomes, List<Certificat> certificats,
                                               @RequestParam("entrepriseMatricule") Long entrepriseMatricule,@RequestParam("instituMatricule") Long instituMatricule,@RequestParam("cfMatricule") Long cfMatricule){

        return new ResponseEntity<>(this.employeeService.addEmployee(employe,posteMatricule,poleMatricule,
                experiences,diplomes,certificats,entrepriseMatricule,instituMatricule,cfMatricule
                ), HttpStatus.CREATED);
    }*/

    @PutMapping("/editEmployee")
    public ResponseEntity<Employe> updateEmployee(@RequestBody Employe employee)
    {
        return new ResponseEntity<>(this.employeeService.updateEmployee(employee),HttpStatus.OK);
    }

    @PutMapping("/editCertificatDetails")
    public ResponseEntity<CertificatDetails> updateCertificatDetails(@RequestBody CertificatDetails certifDetail)
    {
        return new ResponseEntity<>(this.certificatDetailsService.updateCertificatDetails(certifDetail),HttpStatus.OK);
    }

    @DeleteMapping("/deleteCertifDetails")
    public ResponseEntity<?> deleteCertificatDetail(@RequestParam("id") Long id){
        this.certificatDetailsService.deleteCertificatDetails(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editDiplomeDetails")
    public ResponseEntity<DiplomeDetails> updateCertificatDetails(@RequestBody DiplomeDetails diplomeDetail)
    {
        return new ResponseEntity<>(this.diplomeDetailService.updateDiplomeDetails(diplomeDetail),HttpStatus.OK);
    }

    @DeleteMapping("/deleteDiplomeDetails")
    public ResponseEntity<?> deleteDiplomeDetail(@RequestParam("id") Long id){
        this.diplomeDetailService.deleteDiplomeDetails(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
