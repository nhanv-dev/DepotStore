package com.spring.server.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.server.security.jwt.JwtAuthenticationEntryPoint;
import com.spring.server.security.jwt.JwtAuthenticationFilter;
import com.spring.server.security.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods(CorsConfiguration.ALL);
    }

    @Bean
    public JwtAuthenticationFilter authenticationJwtTokenFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(withDefaults()).csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        // Cho phép truy cập vào các đường dẫn tài nguyên tĩnh mà không cần xác thực
                        .requestMatchers("/static/**", "/public/**", "/favicon.ico").permitAll()

                        // Các đường dẫn cần xác thực
                        .requestMatchers("/auth/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/products/**").permitAll()
                        .requestMatchers("/api/categories/**").permitAll()
                        .requestMatchers("/api/search/**").permitAll()
                        .requestMatchers("/api/users/**").permitAll()
                        .requestMatchers("/api/shops/**").permitAll()
                        .requestMatchers("/api/order-status/**").permitAll()
                        .anyRequest().authenticated());
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler));

        return http.build();
    }

}
