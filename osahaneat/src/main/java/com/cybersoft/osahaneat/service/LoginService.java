package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.Role;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.payload.Request.SignupRequest;
import com.cybersoft.osahaneat.payload.Respone.RoleReponse;
import com.cybersoft.osahaneat.payload.Respone.UserReponse;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.imp.LogninServiceImp;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LogninServiceImp {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public List<UserReponse> getAllUser() {
        List<Users> usersList = userRepository.findAll();
        List<UserReponse> userReponsesList = new ArrayList<>();
        for (Users users: usersList) {
            UserReponse userReponse = new UserReponse();
            userReponse.setId(users.getId());
            userReponse.setUsername(users.getUsername());
            userReponse.setPassword(users.getPassword());
            userReponse.setFullName(users.getFullName());

            RoleReponse roleReponse  = new RoleReponse();
            roleReponse.setRoleName(users.getRoles().getRoleName());

            userReponse.setRoleReponse(roleReponse);
            userReponsesList.add(userReponse);

        }
        return userReponsesList;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        List<Users> usersList = userRepository.findByUsernameAndPassword(username,password);

        return usersList.size()>0;
    }

    @Override
    public boolean createUser(SignupRequest signupRequest) {
        Users users = new Users();
        Role role = new Role();
        role.setId(signupRequest.getRoleId());
        users.setFullName(signupRequest.getFullName());
        users.setUsername(signupRequest.getEmail());
        users.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        users.setRoles(role);
        try {
            userRepository.save(users);
            return true;
        }catch (Exception e){
            e.getLocalizedMessage();
            return false;
        }
    }
}
