package com.cybersoft.osahaneat.controller;


import com.cybersoft.osahaneat.payload.BaseReponse;
import com.cybersoft.osahaneat.payload.Request.SignupRequest;
import com.cybersoft.osahaneat.service.imp.LogninServiceImp;
import com.cybersoft.osahaneat.util.JWTHelper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Set;
@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LogninServiceImp loginServiceImp;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTHelper jwtHelper;
    @Autowired
    private Gson gson;
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
            authenticationManager.authenticate(authenticationToken);

            //// get List roles be saved from SecurityContextHolder when AUthemanager authen sucsess
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();// get Authen on securityContext
            List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();// get roles on list Authen
            String jsonRoles = gson.toJson(roles);
            String token = jwtHelper.generateToken(jsonRoles);
            System.out.println(roles);
//            HttpHeaders responseHeaders = new HttpHeaders();
//            responseHeaders.set("Authorization", token);

            BaseReponse baseResponse = new BaseReponse();
            baseResponse.setStatusCode(200);
            baseResponse.setSuccess(true);
            baseResponse.setMessage("Đăng nhập thành công");
            baseResponse.setObject(token);
            return new ResponseEntity<>(baseResponse ,HttpStatus.OK);
        }catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }

        //-------------create key -----------------
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        //or HS384.key() or HS512.key()
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println("Kiểm tra: "+secretString);




    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        BaseReponse baseReponse = new BaseReponse();
        baseReponse.setObject(loginServiceImp.createUser(signupRequest));
        return new ResponseEntity<>(baseReponse, HttpStatus.OK);
    }
}
