package com.secondlife.domain.openAPI.controller;

import com.secondlife.domain.openAPI.dto.OpenAPIRequestDto;
import com.secondlife.domain.openAPI.service.OpenAPIService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class OpenAPI {

    private final OpenAPIService openAPIService;

    @PostMapping("/open")
    public String callApiWithJson(@RequestBody OpenAPIRequestDto requestDto) {
        return openAPIService.getJsonDataFromOpenAPI(requestDto.getOpenAPI());
    }

}
