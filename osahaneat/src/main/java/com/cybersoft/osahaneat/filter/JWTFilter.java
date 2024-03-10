package com.cybersoft.osahaneat.filter;

import com.cybersoft.osahaneat.util.JWTHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTHelper jwtHelper;
    @Autowired
    private Gson gson;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//       get key "Authorization"
        try {
//            response.addHeader("Access-Control-Allow-Origin", "*");
//            response.addHeader("Access-Control-Allow-Methods","*");
//            response.addHeader("Access-Control-Allow-Credentials","true");
//            response.addHeader("Access-Control-Allow-Headers","Content-Type,Authorization");
            // Lấy token từ header
            String token = getTokenFromHeaders(request);

            if (StringUtils.hasText(token)) {
                // Phân tích token
                String data = jwtHelper.parserToken(token);

                if (data != null && !data.isEmpty()) {
                    // Tạo authentication token
                    Type listType = new TypeToken<ArrayList<SimpleGrantedAuthority>>() {
                    }.getType();
                    List<GrantedAuthority> roles = gson.fromJson(data, listType);
                    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken("", "", roles);

                    // Thiết lập authentication vào SecurityContext
                    SecurityContext securityContextHolder = SecurityContextHolder.getContext();
                    securityContextHolder.setAuthentication(userToken);
                }
            }
            // Tiếp tục xử lý chuỗi filter
            filterChain.doFilter(request, response);


        } catch (ExpiredJwtException e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token đã hết hạn");
        } catch (UnsupportedJwtException e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token không hợp lệ");
        } catch (MalformedJwtException e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token bị sai định dạng");
        } catch (SignatureException e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Chữ ký token không hợp lệ");
        } catch (Exception e) {
            // Xử lý các exception khác
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lỗi hệ thống");
        }

    }

    private String getTokenFromHeaders(HttpServletRequest request){
        String token = null;
        String headerValue = request.getHeader("Authorization");
        if (StringUtils.hasText(headerValue) && headerValue.startsWith("Bearer ")) {
            token = headerValue.substring(7);
        }
        else{
            System.out.println("Bear không tồn tại");
        }
        return token;
    }
    //// This example with veriable hard
//                    List<GrantedAuthority> grantedAuthorityListRoles = new ArrayList<>();
//                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
//                    grantedAuthorityListRoles.add(grantedAuthority);
}
