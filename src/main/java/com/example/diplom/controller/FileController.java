package com.example.diplom.controller;


import com.example.diplom.dto.response.FileResponse;
import com.example.diplom.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file/v1")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam MultipartFile file) {
            FileResponse fileResponse = fileService.uploadFile(file);
            return ResponseEntity.ok(fileResponse);
    }

    @GetMapping("/load")
    public ResponseEntity<Resource> loadFile(@RequestParam String key) throws IOException {
        Resource resource = fileService.loadFile(key);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/uri")
    public ResponseEntity<String> getS3Url(@RequestParam String key) {
        String uri = fileService.getUrl(key);
        return ResponseEntity.ok(uri);
    }
}
