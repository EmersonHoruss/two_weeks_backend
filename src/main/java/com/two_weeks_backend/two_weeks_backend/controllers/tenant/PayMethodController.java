package com.two_weeks_backend.two_weeks_backend.controllers.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method.PayMethodActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method.PayMethodCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method.PayMethodShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.tenant.pay_method.PayMethodUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.PayMethod;
import com.two_weeks_backend.two_weeks_backend.services.tenant.PayMethodService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/pay_methods")
public class PayMethodController extends
        BaseControllerImplementation<PayMethod, PayMethodCreateDTO, PayMethodShowDTO, PayMethodUpdateDTO, PayMethodActivatedDTO> {
    @Autowired
    private PayMethodService payMethodService;

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody PayMethodActivatedDTO dto) {
        PayMethod payMethod = this.payMethodService.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(payMethod.asShowDTO()));
    }
}
