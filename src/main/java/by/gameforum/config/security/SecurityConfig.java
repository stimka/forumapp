package by.gameforum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String USERS_BY_USERNAME_QUERY =
            "select username, password, is_active from user where username=?";

    private static final String AUTHORIZES_BY_USERNAME_QUERY =
            "select u.username, concat('ROLE_',(select name from user_role where id = uhr.role_id)) from user u inner join user_has_role uhr on u.id = uhr.user_id where username=?";

    private static final String[] PAGES_ONLY_FOR_AUTHORIZED_USERS = {"/user/**",
            "/topic/new/**",
            "/topic/delete/**",
            "/section/delete/**",
            "/section/new/**",
            "/post/**"};

    private static final String[] LIST_OF_PAGES_ONLY_FOR_ADMINS = {"/admin/**",
            "/users/**",
            "/section/new"};

    private static final String[] LIST_OF_AUTHORIZED_ROLES = {"USER",
            "ADMIN"};

    private static final String[] LIST_OF_ADMINS_ROLES = {"HEAD_ADMIN",
            "ADMIN"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").and().rememberMe().tokenValiditySeconds(2419200).key(
                "f0rumKey").and().logout().and().authorizeRequests().antMatchers(
                PAGES_ONLY_FOR_AUTHORIZED_USERS).hasAnyRole(LIST_OF_AUTHORIZED_ROLES)
                .antMatchers(LIST_OF_PAGES_ONLY_FOR_ADMINS).hasAnyRole(LIST_OF_ADMINS_ROLES);

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
                USERS_BY_USERNAME_QUERY).authoritiesByUsernameQuery(AUTHORIZES_BY_USERNAME_QUERY)
                .passwordEncoder(passwordEncoder);
    }

}
