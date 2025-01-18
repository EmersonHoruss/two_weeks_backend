package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellActivatedDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellUpdateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.controllers.BaseControllerImplementation;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.services.sell.SellService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sells")
public class SellController
		extends BaseControllerImplementation<Sell, SellCreateDTO, SellShowDTO, SellUpdateDTO, SellActivatedDTO> {
	@Autowired
	SellService service;

	@PatchMapping("")
	public ResponseEntity<ResponseDTO> setActivation(@Valid @RequestBody SellActivatedDTO dto) {
		Sell sell = this.service.setActivation(dto.asEntity());
		return ResponseEntity.ok(new ResponseDTO(sell.asShowDTO()));
	}
}
