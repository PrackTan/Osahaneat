package com.cybersoft.osahaneat.controller;


import com.cybersoft.osahaneat.payload.BaseReponse;
import com.cybersoft.osahaneat.payload.Request.MenuRequest;
import com.cybersoft.osahaneat.service.MenuService;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuServiceImp menuServiceImp;
    @Autowired
    FileServiceImp fileServiceImp;
    @PostMapping("")
    public ResponseEntity<?> insertMenu(@ModelAttribute MenuRequest menuRequest, MultipartFile file){
        boolean isSuccess = menuServiceImp.insertMenu(menuRequest,file);
        BaseReponse baseReponse = new BaseReponse();

        baseReponse.setStatusCode(isSuccess?200:400);
        baseReponse.setSuccess(isSuccess?true:false);
        baseReponse.setMessage(isSuccess?"Inserted":"Error insert");
        baseReponse.setObject(isSuccess);
        return new ResponseEntity<>(baseReponse, HttpStatus.OK);
    }
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<?> getFilleResults(@PathVariable String filename){
        Resource resource =  fileServiceImp.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}
