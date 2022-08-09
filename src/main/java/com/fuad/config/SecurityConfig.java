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
@ComponentScan(basePackages = {"com.fuad.config", "com.fuad.service"})
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder);

        // For in memory authentication
        // managerBuilder.inMemoryAuthentication().withUser("fuad").password("{noop}1234").roles("ADMIN");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                    .headers().frameOptions().sameOrigin()
                .and()
                        // Permitting all static resources to be accessed publicly
                    .authorizeRequests()
                    .antMatchers("/images/**", "/css/**", "/js/**").permitAll()
                    .antMatchers("/auth/**").permitAll()
                        // We are restricting endpoints for individual roles.
                        // Only users with allowed roles will be able to access individual endpoints.
                .and()
                    .authorizeRequests()
                    .antMatchers("/location/**").hasRole("ADMIN")
                    .anyRequest().authenticated()

                        // configuring our login form
                .and()
                    .formLogin(form -> form
                            .loginPage("/auth/login")
                            .permitAll()
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .loginProcessingUrl("/auth/login-process")
                            .successHandler(authSuccessHandler)
                            .permitAll()
                    )

                    .logout(logout -> logout
                            .logoutUrl("/auth/logout")
                            .logoutSuccessUrl("/auth/login?logout")
                            .permitAll()
                    )
                    .build();
//        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).httpBasic(withDefaults());
    }

}
