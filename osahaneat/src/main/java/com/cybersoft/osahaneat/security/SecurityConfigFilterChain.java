    package com.cybersoft.osahaneat.security;


    import com.cybersoft.osahaneat.filter.JWTFilter;
    import com.cybersoft.osahaneat.provider.CustomAuthenProvider;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.access.channel.ChannelProcessingFilter;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    import java.util.Arrays;

    @Configuration// tầng config(config chạy đầu tiên) chạy và đọc file này
    @EnableWebSecurity // để mở can thiệp security
    public class SecurityConfigFilterChain {
        ///--------------------Using AuthenticationManager used our CustomAuthenProvider------------
        @Autowired
        CustomAuthenProvider customAuthenProvider;
        @Autowired
        JWTFilter jwtFilter;
    //    -------------- Inject customAuthenProvider in AuthenticationManager---------------
        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                    .authenticationProvider(customAuthenProvider)
                    .build();
        }

    //--------------------Security----------------------
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity https) throws Exception{
            return https
                    .cors(Customizer.withDefaults())
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(request -> request
                        .requestMatchers("/login/**").permitAll()
//                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers(HttpMethod.GET,"/restaurant/**","/restaurant/file/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/restaurant/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/user").hasRole("ADMIN")
                        .requestMatchers("/menu/**").hasRole("ADMIN")
                        .requestMatchers("/category/**","/orders").permitAll()
                        .anyRequest().authenticated()
                    )
//                    .addFilterBefore(new CORSConfig(), ChannelProcessingFilter.class)
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();

        }
//        @Bean
//        public CorsConfigurationSource corsConfigurationSource() {
//            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.setAllowedOrigins(Arrays.asList("*"));
//            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//            configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-auth-token"));
//            configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            source.registerCorsConfiguration("/**", configuration);
//            return source;
//        }
    //    -------------------- kind of Encoder----------
        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

    }
