package com.alphanah.alphanahbackend.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AmazonS3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${application.bucket.name}")
    private String bucketName;

    public String saveFile(MultipartFile file) {
        String id = UUID.randomUUID().toString();
        List<String> contentTypeSplit = Arrays.asList(file.getOriginalFilename().split("\\."));
        String contentTypeName = contentTypeSplit.get(contentTypeSplit.size() - 1);
        String fileName = id + "." + contentTypeName;
        File convertedFile = convertMultiPartToFile(file);
        PutObjectResult result = amazonS3.putObject(bucketName, fileName, convertedFile);
        return "/" + fileName;
    }

    private File convertMultiPartToFile(MultipartFile file) {
        try {
            File convertedFile = new File(file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
            fos.close();
            return convertedFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
