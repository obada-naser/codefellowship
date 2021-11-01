package com.example.codefellowship.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

        return passwordEncoder;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers("/*").permitAll()

                .antMatchers("/login*").permitAll()
                .antMatchers("/signup*").permitAll()
                .antMatchers("/style.css").permitAll()
                .antMatchers("/Components").permitAll()
//                .antMatchers("/profile").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/profile")
                .failureUrl("/login")
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID");


//
//            http.cors().disable()
//                    .csrf().disable()
//                    .authorizeRequests()
//                    .antMatchers( "/login", "/signup","/").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/perform_login")
//                    .defaultSuccessUrl("/profile", true)
//                    .failureUrl("/error")
//                    .and()
//                    .logout()
//                    .logoutUrl("/perform_logout")
//                    .deleteCookies("JSESSIONID");
        }
    }


