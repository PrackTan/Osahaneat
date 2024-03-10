package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.payload.BaseReponse;
import com.cybersoft.osahaneat.payload.Respone.UserReponse;
import com.cybersoft.osahaneat.service.imp.LogninServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    LogninServiceImp logninServiceImp;
    @GetMapping("")
    public ResponseEntity<?> getAllUsers(UserReponse userReponse){
        List<?> listUsers = logninServiceImp.getAllUser();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> insertUser(){
        return new ResponseEntity<>("insert user COMPLETE",HttpStatus.OK);
    }
}
