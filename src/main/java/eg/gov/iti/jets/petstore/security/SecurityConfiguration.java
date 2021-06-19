package eg.gov.iti.jets.petstore.security;


import eg.gov.iti.jets.petstore.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthorizationFilter authorizationFilter;


    @Autowired
    public SecurityConfiguration(AuthService userService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthorizationFilter authorizationFilter) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authorizationFilter = authorizationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**")
//                .antMatchers("/categories").hasRole("CUSTOMER")
//                .antMatchers("/auth/signUp","/auth/authenticate")
                .permitAll()
                .anyRequest()
                .authenticated()
        ;
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
//                .addFilter(new AuthenticationFilter(authenticationManager(), jwtUtil))

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}