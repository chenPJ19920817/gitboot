package start.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select \n" +
                "\t\tur.user_id,\n" +
                "\t\tu.user_id,\n" +
                "\t\tu.account,\n" +
                "\t\tu.password,\n" +
                "\t\tGROUP_CONCAT(ur.role_id) as roleIds,\n" +
                "\t\tGROUP_CONCAT(r.role_name) as roleNames \n" +
                "from \n" +
                "\tuser_role ur \n" +
                "\tLEFT JOIN login_user u on u.user_id=ur.user_id \n" +
                "\tLEFT JOIN role r on r.role_id=ur.role_id \n" +
                "GROUP BY ur.user_id");
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer =
                auth.inMemoryAuthentication();
        for(Map<String, Object> map:maps){
            String account = (String) map.get("account");
            String password = (String) map.get("password");
            String roleNames = (String) map.get("roleNames");
            authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer.withUser(account).password(new BCryptPasswordEncoder().encode(password)).roles(roleNames.split(","));
        }

        /*InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer = auth.inMemoryAuthentication();
        authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("admin","USER");

        authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer
                .withUser("xxmzz").password(new BCryptPasswordEncoder().encode("123")).roles("USER");*/
    }
}