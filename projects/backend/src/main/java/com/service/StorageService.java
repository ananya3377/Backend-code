package com.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

    private final Path base;

    // Constructor ensures base directory is initialized safely
    public StorageService(@Value("${estatevault.storage.base:uploads}") String baseDir) {
        this.base = Paths.get(baseDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.base); // Create if not exists
        } catch (IOException e) {
            throw new IllegalStateException("Could not initialize storage at: " + this.base, e);
        }
    }

    /**
     * Store a file inside a subfolder of the base directory.
     *
     * @param file      The uploaded MultipartFile
     * @param subfolder Optional subfolder name (defaults to "default")
     * @return Absolute path of the stored file
     * @throws IOException if writing fails
     */
    public String store(MultipartFile file, String subfolder) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Cannot store empty file");
        }

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path folder = base.resolve(subfolder == null ? "default" : subfolder).normalize();
        Files.createDirectories(folder);

        Path dest = folder.resolve(filename).normalize();
        Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

        return dest.toAbsolutePath().toString();
    }
}
