package com.gmail.burinigor7.crudservice.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    private final Path root = Paths.get("uploads");

    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public void saveImage(MultipartFile file, Long fpId) {
        new File(root + File.separator + "images" + File.separator + fpId).mkdir();
        try {
            Files.copy(file.getInputStream(), this.root.resolve("images" + File.separator + fpId + File.separator + file.getOriginalFilename()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void saveModerationFile(MultipartFile file, Long fpId) {
        new File(root + File.separator + "moderation" + File.separator + fpId).mkdir();
        try {
            Files.copy(file.getInputStream(), this.root.resolve("moderation" + File.separator + fpId + File.separator + file.getOriginalFilename()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void saveOtherFile(MultipartFile file, Long fpId) {
        new File(root + File.separator + "other" + File.separator + fpId).mkdir();
        try {
            Files.copy(file.getInputStream(), this.root.resolve("other" + File.separator + fpId + File.separator + file.getOriginalFilename()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void saveReportFile(MultipartFile file, Long payoutRequestId) {
        new File(root + File.separator + "report" + File.separator + payoutRequestId).mkdir();
        try {
            Files.copy(file.getInputStream(), this.root.resolve("report" + File.separator + payoutRequestId
                    + File.separator + file.getOriginalFilename()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Stream<Path> loadAllModerationFiles(Long fpId) {
        File[] files = root.resolve("moderation" + File.separator + fpId).toFile().listFiles();
        if (files != null && files.length > 0) {
            return Arrays.stream(files).map(File::toPath);
        } else return Stream.empty();
    }

    public Stream<Path> loadAllOtherFiles(Long fpId) {
        File[] files = root.resolve("other" + File.separator + fpId).toFile().listFiles();
        if (files != null && files.length > 0) {
            return Arrays.stream(files).map(File::toPath);
        } else return Stream.empty();
    }

    public Stream<Path> loadAllImages(Long fpId) {
        File[] files = root.resolve("images" + File.separator + fpId).toFile().listFiles();
        if (files != null && files.length > 0) {
            return Arrays.stream(files).map(File::toPath);
        } else return Stream.empty();
    }

    public Stream<Path> loadAllReportFiles(Long id) {
        File[] files = root.resolve("report" + File.separator + id).toFile().listFiles();
        if (files != null && files.length > 0) {
            return Arrays.stream(files).map(File::toPath);
        } else return Stream.empty();
    }
}