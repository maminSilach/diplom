package com.example.diplom.service;


import com.example.diplom.dto.response.FileResponse;
import com.example.diplom.exception.AwsException;
import com.example.diplom.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    public static final String BUCKET_NAME = "image";

    private final S3Client s3Client;
    private final FileMapper fileMapper;

    @Value("${aws.host}")
    private String host;

    @Value("${aws.region.static}")
    private String region;

    @SneakyThrows
    public FileResponse uploadFile(MultipartFile file) {
        byte[] bytes = file.getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        String key = file.getOriginalFilename() + "_" + UUID.randomUUID();

        log.info("Start put file in S3 with next key = {} to bucket = {}", key, BUCKET_NAME);
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build();

        putObject(putObjectRequest, inputStream, bytes);
        String url = getUrl(key);

        return fileMapper.toFileResponse(url, key);
    }

    public Resource loadFile(String key) {
        log.info("Loading file from bucket = {} with key = {}", BUCKET_NAME, key);

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build();

        byte[] response = getObject(getObjectRequest);
        return new ByteArrayResource(response);
    }

    public String getUrl(String key) {
        return s3Client.utilities()
                .getUrl(GetUrlRequest.builder()
                        .bucket(BUCKET_NAME)
                        .region(Region.of(region))
                        .endpoint(URI.create(host))
                        .key(key)
                        .build()
                ).toExternalForm();
    }

    private void putObject(PutObjectRequest putObjectRequest, InputStream inputStream, byte[] bytes) {
        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, bytes.length));
        } catch (RuntimeException e) {
            log.error("Error while trying to put file in S3", e);
            throw new AwsException(e.getMessage());
        }
    }

    private byte[] getObject(GetObjectRequest getObjectRequest) {
        try {
            return s3Client.getObject(getObjectRequest).readAllBytes();
        } catch (IOException | RuntimeException e) {
            log.error("Error while trying to load file from S3", e);
            throw new AwsException(e.getMessage());
        }
    }
}
