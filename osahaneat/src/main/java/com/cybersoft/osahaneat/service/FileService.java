package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
@Slf4j
public class FileService implements FileServiceImp {
    /// SPring boot support for Path (url) and Resource(file)
    @Value("${path.upload.file}")
    private String rootPath;
    private Path root;
    public void init(){
        try{
            root = Paths.get(rootPath);
            if(Files.notExists(root)){
                Files.createDirectories(root);
            }
        }catch (Exception e){
            log.info("Error create folder root"+e.getMessage());
        }
    }
    @Override
    public boolean saveFile(MultipartFile multipartFile) {
        try {
            init();
            Files.copy(multipartFile.getInputStream(), root.resolve(multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return true;
        }catch (Exception e){
            log.error("Lỗi không save được file "+e.getMessage());
            return false;
        }
    }

    @Override
    public Resource loadFile(String fileName) {
        //resolve = "\\" + fileName
        try {
            init();
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Lỗi load file"+e);

        }
        return null;
    }
}
