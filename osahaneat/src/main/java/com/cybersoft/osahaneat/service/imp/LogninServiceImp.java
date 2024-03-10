package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.payload.Request.SignupRequest;
import com.cybersoft.osahaneat.payload.Respone.UserReponse;

import java.util.List;

public interface LogninServiceImp {
    List<UserReponse> getAllUser();
    boolean checkLogin(String username,String password);
    boolean createUser(SignupRequest signupRequest);
}
