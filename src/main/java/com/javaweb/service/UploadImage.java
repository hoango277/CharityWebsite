package com.javaweb.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadImage {
    String uploadImage(MultipartFile file) throws IOException;
}
