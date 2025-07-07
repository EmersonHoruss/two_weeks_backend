package com.two_weeks_backend.two_weeks_backend.DTOs.responses;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
public class ResponseDTO {
    private final Object content;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageableDTO pageable;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SortDTO sort;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String query;

    public ResponseDTO(Object content, @SuppressWarnings("rawtypes") Page page, String query){
        this.content = content;
        if(page != null) {
            this.pageable = new PageableDTO(page);
            this.sort = new SortDTO(page);
        }
        if(query == null) {
            this.query = "";
        } else {
            this.query = query;
        }
    }

    public ResponseDTO(Object content){
        this.content = content;
    }
}
