package com.two_weeks_backend.two_weeks_backend.controllers.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellCreateDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.entities.sell.sell.SellShowDTO;
import com.two_weeks_backend.two_weeks_backend.DTOs.responses.ResponseDTO;
import com.two_weeks_backend.two_weeks_backend.entities.sell.Sell;
import com.two_weeks_backend.two_weeks_backend.services.sell.SellService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sells")
public class SellController {
	@Autowired
	SellService service;

	@PostMapping("")
	public ResponseEntity<ResponseDTO> create(@Valid @RequestBody SellCreateDTO sellCreateDTO,
			UriComponentsBuilder uriBuilder, HttpServletRequest request) {
		Sell sell = this.service.create(sellCreateDTO);
		String requestUri = request.getRequestURI() + "/{id}";
		URI uri = uriBuilder.path(requestUri).buildAndExpand(sell.getId()).toUri();
		SellShowDTO dto = sell.asShowDTO();
		return ResponseEntity.created(uri).body(new ResponseDTO(dto));
	}

	// @PutMapping("/")
}
