package com.ditraacademy.travelagency.core.uploads;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @Autowired
    UploadFileService uploadService ;

    @PostMapping ("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file ){
        return uploadService.uploadFile(file);
    }
}
