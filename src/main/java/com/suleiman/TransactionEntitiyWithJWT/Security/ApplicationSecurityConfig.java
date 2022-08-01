package com.suleiman.TransactionEntitiyWithJWT.Security;

import com.suleiman.TransactionEntitiyWithJWT.Auth.ApplicationUserService;
import com.suleiman.TransactionEntitiyWithJWT.JWT.JwtTokenVerifier;
import com.suleiman.TransactionEntitiyWithJWT.JWT.JwtUsernameAndPasswordAuthenticationFilter;
import com.suleiman.TransactionEntitiyWithJWT.Utils.ApplicationUserPermission;
import com.suleiman.TransactionEntitiyWithJWT.Utils.ApplicationUserRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder , ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .csrf().disable()
              .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
              .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
              .authorizeHttpRequests()
              .antMatchers("/","index","/css/*","/js/*").permitAll()
//              .antMatchers(HttpMethod.GET,"/transaction/api/v1/**").hasAnyRole(ApplicationUserRoles.DEVELOPER.name(),ApplicationUserRoles.SENIOR_DEVELOPER.name())
              .antMatchers(HttpMethod.DELETE,"/transaction/api/v1/**").hasAuthority(ApplicationUserPermission.DELETE_PERMISSION.getPermission())
              .antMatchers(HttpMethod.PUT,"/transaction/api/v1/**").hasAuthority(ApplicationUserPermission.PUT_PERMISSION.getPermission())
              .antMatchers(HttpMethod.POST,"/transaction/api/v1/**").hasAuthority(ApplicationUserPermission.POST_PERMISSION.getPermission())
              .antMatchers(HttpMethod.POST,"/transaction/api/v1/**").hasAuthority(ApplicationUserPermission.GET_PERMISSION.getPermission())
              .anyRequest().authenticated();

    }


}
