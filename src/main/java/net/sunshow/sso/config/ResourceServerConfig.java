package net.sunshow.sso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * author: sunshow.
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers().antMatchers("/api/**")
                .and().authorizeRequests()
                .antMatchers("/api/**").authenticated();//.access("#oauth2.hasScope('resource:read')");
        ;
    }

}
