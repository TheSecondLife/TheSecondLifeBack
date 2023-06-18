package com.secondlife.domain.openAPI.controller;

import com.secondlife.domain.openAPI.dto.OpenAPIRequestDto;
import com.secondlife.domain.openAPI.service.OpenAPIService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/open")
@CrossOrigin("*")
public class OpenAPIController {

    private final OpenAPIService openAPIService;

    @PostMapping("/")
    public String callApiWithJson(@RequestBody OpenAPIRequestDto requestDto) {
        return openAPIService.getJsonDataFromOpenAPI(requestDto.getOpenAPI());
    }

}
