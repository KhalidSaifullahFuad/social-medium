package com.fuad.config;


import com.fuad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.fuad.service"})
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Autowired
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());

        // For in memory authentication
        // managerBuilder.inMemoryAuthentication().withUser("fuad").password("{noop}1234").roles("ADMIN");

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).httpBasic(withDefaults());
        http
                .authorizeRequests(auth -> auth
                        .antMatchers("/resources/**", "/temp/**").permitAll()
                        .antMatchers("/", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/login-process")
                        .defaultSuccessUrl("/")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
