package shcherbakov.sergey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import shcherbakov.sergey.services.user.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(apiUserDetailsService());
    }

    @Bean 
    public UserDetailsService apiUserDetailsService() {
        return new UserDetailsServiceImpl();      
    } 

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.csrf()
    		.disable();
        http
            .authorizeRequests()
            	.antMatchers("/", "/register", "/addUser").permitAll()
                .antMatchers("/user/**", "/logout").authenticated()
            .and()
            	.formLogin()
            	.loginPage("/login")
            	.failureUrl("/login/error")
            	.permitAll()
            	.usernameParameter("login")
            	.passwordParameter("password")
            	.loginProcessingUrl("/j_spring_security_check")
        	.and()
            	.logout()
            	.logoutUrl("/logout");
        ;
    }
}
