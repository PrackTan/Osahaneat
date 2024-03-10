package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.payload.BaseReponse;
import com.cybersoft.osahaneat.payload.Respone.CategoryReponse;
import com.cybersoft.osahaneat.service.imp.CategoryServiceImp;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryServiceImp;
    @Autowired
    FileServiceImp fileServiceImp;
    @GetMapping("")
    public ResponseEntity<?> getHomeCategory(){
        BaseReponse baseReponse = new BaseReponse();
        List<CategoryReponse> categoryReponseList = categoryServiceImp.getCategory();
        baseReponse.setStatusCode(categoryReponseList.isEmpty()?400:200);
        baseReponse.setSuccess(categoryReponseList.isEmpty()?false:true);
        baseReponse.setMessage("Get list category");
        baseReponse.setObject(categoryReponseList);
        return new ResponseEntity<>(baseReponse, HttpStatus.OK);
    }
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<?> getFilleResults(@PathVariable String filename){
        Resource resource =  fileServiceImp.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}
