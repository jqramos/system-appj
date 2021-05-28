package org.port.web.auth;

import org.port.services.user.CustomOidcUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurtyConfig extends WebSecurityConfigurerAdapter {

    private final CustomOidcUserService oidcUserService;

    public SecurtyConfig(CustomOidcUserService oidcUserService) {
        this.oidcUserService = oidcUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**", "/login**","/callback/", "/webjars/**", "/error**", "/oauth2/authorization/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .oidcUserService(oidcUserService);
    }
}
