package com.two_weeks_backend.two_weeks_backend.controllers.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company.CompanyActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company.CompanyCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company.CompanyShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.company.company.CompanyUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.company.Company;
import com.two_weeks_backend.two_weeks_backend.services.company.CompanyService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/companies")
public class CompanyController extends
        BaseControllerImplementation<Company, CompanyCreateDTO, CompanyShowDTO, CompanyUpdateDTO, CompanyActivatedDTO> {
    @Autowired
    private CompanyService companyService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody CompanyActivatedDTO dto) {
        Company company = this.companyService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(company.asShowDTO()));
    }
}
