package com.backend.tokokantjil.configurations;

import com.backend.tokokantjil.repositories.UserRepository;
import com.backend.tokokantjil.filters.JwtRequestFilter;
import com.backend.tokokantjil.utilities.JwtService;
import com.backend.tokokantjil.security.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepository) {
        this.jwtService = service;
        this.userRepository = userRepository;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailService, PasswordEncoder passwordEncoder) {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(userDetailService);
        return new ProviderManager(auth);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers("/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/dishes", "/products").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,
                                        "/addresses/**",
                                        "/caterings/**",
                                        "/customers/**",
                                        "/dishes/**",
                                        "/invoices/**",
                                        "/orders/**",
                                        "/products/**"
                                ).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,
                                        "/addresses/**",
                                        "/caterings/**",
                                        "/customers/**",
                                        "/dishes/**",
                                        "/invoices/**",
                                        "/orders/**",
                                        "/products/**"
                                ).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("/users", "/users/**", "/roles", "/roles/**").hasRole("ADMIN")
                                .requestMatchers(
                                        "/addresses",
                                        "/addresses/**",
                                        "/caterings",
                                        "/caterings/**",
                                        "/caterings/*/**",
                                        "/caterings/*/prices/**",
                                        "/customers",
                                        "/customers/**",
                                        "customers/*/**",
                                        "/dishes",
                                        "/dishes/**",
                                        "/dishes/*/**",
                                        "/dishes/*/prices/**",
                                        "/invoices",
                                        "/invoices/**",
                                        "/invoices/*/**",
                                        "/invoices/customer/**",
                                        "/orders",
                                        "/orders/**",
                                        "/orders/*/**",
                                        "/orders/*/prices/**",
                                        "/products",
                                        "/products/**",
                                        "/products/*/**"
                                ).hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("/authenticate").permitAll()
                                .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
