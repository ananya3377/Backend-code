package com.estatevault.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private final Path base;

    public StorageService(@Value("${estatevault.storage.base}") String baseDir) throws IOException {
        this.base = Paths.get(baseDir);
        Files.createDirectories(base);
    }

    public String store(MultipartFile file, String subfolder) throws IOException {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path folder = base.resolve(subfolder == null ? "default" : subfolder);
        Files.createDirectories(folder);
        Path dest = folder.resolve(filename);
        Files.copy(file.getInputStream(), dest);
        return dest.toAbsolutePath().toString();
    }
}