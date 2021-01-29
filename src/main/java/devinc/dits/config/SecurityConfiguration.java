package devinc.dits.config;

import devinc.dits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private SuccessHandler successHandler;

    private UserService userService;

    @Autowired
    public void setSuccessHandler(SuccessHandler customSuccessHandler) {
        this.successHandler = customSuccessHandler;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return  userDetailsService();
    }

    @Autowired
    DataSource dataSource;

//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService)
//                .passwordEncoder(passwordEncoder);
//    }

    //////////////////логин и пароль записаны вручную для тестирования
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
               .withUser("user").password(passwordEncoder.encode("user")).roles("USER").
                and()
                .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").
                and()
                .withUser("tutor").password(passwordEncoder.encode("tutor")).roles("TUTOR");
    }
    /////////////////

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('USER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/tutor/**").access("hasRole('TUTOR')")
                .antMatchers("/resources/**").permitAll()
                .anyRequest().permitAll();
//
        http.formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
         //       .failureHandler(authFailureHandler)
                .usernameParameter("username").passwordParameter("password")
                .usernameParameter("username").passwordParameter("password")
                .and().csrf().disable();
//
        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);
    }

}
