package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.payload.Respone.RoleReponse;
import com.cybersoft.osahaneat.payload.Respone.UserReponse;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.imp.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserImp {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserReponse> getAllUsers() {
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
}
