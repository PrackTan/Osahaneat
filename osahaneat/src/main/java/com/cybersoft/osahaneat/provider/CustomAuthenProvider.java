package com.cybersoft.osahaneat.provider;

import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomAuthenProvider implements AuthenticationProvider {
    @Autowired
    UserRepository userRepository;
    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Users users = userRepository.findByUsername(username);
//        checking users
        if(users != null){
            // checking password
            if(passwordEncoder.matches(password,users.getPassword())){
//                Get roles (type author)
                List<GrantedAuthority> grantedAuthorityListRoles = new ArrayList<>();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(users.getRoles().getRoleName());
                grantedAuthorityListRoles.add(grantedAuthority);
//                The kind authorization UsernamePassword
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,users.getPassword(),grantedAuthorityListRoles);
//               Create contextholder get UsernamePasswordAuthenticationToken
                SecurityContextHolder.getContext().setAuthentication(token);
                return token;
            }else {
                 throw new BadCredentialsException("Invalid password");
            }
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
