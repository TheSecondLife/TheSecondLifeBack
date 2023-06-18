package com.secondlife.domain.global.controller;


import com.secondlife.domain.global.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/file")
@CrossOrigin("*")
public class S3UploadController {

    private final S3UploadService uploadService;

}
