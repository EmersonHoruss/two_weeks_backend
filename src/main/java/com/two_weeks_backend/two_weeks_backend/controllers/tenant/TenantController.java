package com.two_weeks_backend.two_weeks_backend.controllers.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.tenant.TenantActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.tenant.TenantCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.tenant.TenantShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.tenant.TenantUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.services.tenant.TenantService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/tenants")
public class TenantController extends
        BaseControllerImplementation<Tenant, TenantCreateDTO, TenantShowDTO, TenantUpdateDTO, TenantActivatedDTO> {
    @Autowired
    private TenantService tenantService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody TenantActivatedDTO dto) {
        Tenant tenant = this.tenantService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(tenant.asShowDTO()));
    }
}
