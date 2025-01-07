package com.two_weeks_backend.two_weeks_backend.controllers.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.provider.ProviderActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.provider.ProviderCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.provider.ProviderShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.purchase.provider.ProviderUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.purchase.Provider;
import com.two_weeks_backend.two_weeks_backend.services.purchase.ProviderService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/providers")
public class ProviderController extends
        BaseControllerImplementation<Provider, ProviderCreateDTO, ProviderShowDTO, ProviderUpdateDTO, ProviderActivatedDTO> {
    @Autowired
    private ProviderService providerService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody ProviderActivatedDTO dto) {
        Provider provider = this.providerService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(provider.asShowDTO()));
    }

}
