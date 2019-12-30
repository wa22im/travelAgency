package com.ditraacademy.travelagency.core.uploads;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class UploadFileService {


    @Value("${UPLOAD_DIR}")
    private Path fileStoragePath ;

    @Value("${SERVER_HOST}")
    private  String serverHost  ;

    public ResponseEntity<?> uploadFile (MultipartFile file ){
        String filename = file.getOriginalFilename() ;
        Path targetLocation = fileStoragePath.resolve(filename);
        try {
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);

        }catch (IOException e ){
            return  new ResponseEntity<>( new ErrorResponseModel("Error"), HttpStatus.BAD_REQUEST);
        }
        String fileUrl = serverHost + filename ;
        System.out.println(fileUrl);
        return   new ResponseEntity< >( fileUrl,HttpStatus.OK);
    }
}
