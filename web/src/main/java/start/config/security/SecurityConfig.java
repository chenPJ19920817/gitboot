package start.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    //注入异常处理类
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录
        http.formLogin()
                //自定义登录页面
                .loginPage("/toLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                //自定义登录逻辑
                .loginProcessingUrl("/doLogin")
                //登录成功后的跳转页面
                .successHandler(new LoginSuccessHandler("/main/loginInfo")); //重定向

        //授权
        http.authorizeRequests()
                //放行登录页面
                .antMatchers("/public/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/doLogin").permitAll()
                //所有请求都必须被认证
                .anyRequest().authenticated();
        //注销
        http.logout()
                //退出登录url
                .logoutUrl("/logout")
                //退出登录成功跳转url
                .logoutSuccessUrl("/toLogin");

        //异常处理（例如权限不足）
        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);  //重定向
        //记住我
        http.rememberMe()
                //自定义登录逻辑
                .userDetailsService(userDetailsService);
        //关闭csrf防护
        http.csrf().disable();
    }
*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

        auth.inMemoryAuthentication()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("admin","USER")
                .and()
                .withUser("xxmzz").password(new BCryptPasswordEncoder().encode("123")).roles("USER");
    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(
//                "/**/*.css",
//                "/**/*.js",
//                "/img/**",
//                "/admin/images/**"
//        );
//    }
}