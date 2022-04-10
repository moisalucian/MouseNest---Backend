package com.example.MouseNest.config;

import com.example.MouseNest.model.Role;
import com.example.MouseNest.service.MailService;
import com.example.MouseNest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    //metoda care manageruieste cine si unde are acces
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable().authorizeRequests()
//                .antMatchers("/registration**",
//                        "/js/**",
//                        "/css/**",
//                        "/img/**",
//                        "/webjars/**").permitAll()
                .antMatchers("/**").permitAll();
//                .antMatchers("/createProduct","/updateProduct/*","/deleteProduct/*", "/home-admin", "/create-admin").hasAuthority(Role.ADMIN.name()/*metoda care converteste enumul in string*/)
//                .anyRequest().authenticated()//aceasta metoda controleaza care pagini sa fie publice si care nu (pentru toate cele de mai sus trebuie sa ne autentificam inainte)
//                .and()
//                .formLogin().loginPage("/login").permitAll()//acest url trebuie specificat si metodei din MainController ("/login")
//                .and()
//                .logout().invalidateHttpSession(true).clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//acest url trebuie specificat si metodei din MainController ("/logout")
//                .logoutSuccessUrl("/login?logout").permitAll();//aceasta linie ne ajuta ca atunci cand facem logout sa ne reintoarca la login
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
