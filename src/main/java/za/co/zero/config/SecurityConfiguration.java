package za.co.zero.config;

/**
 * Created NMandisa Mkhungo by  on 9/1/2016.
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.
                authorizeRequests()
                .antMatchers("/").permitAll();
        httpSecurity.securityContext().disable();
        httpSecurity.headers().disable();
        httpSecurity.headers().xssProtection().disable();
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.csrf().disable();
    }


}
