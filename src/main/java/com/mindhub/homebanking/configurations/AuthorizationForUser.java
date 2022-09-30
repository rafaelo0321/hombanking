package com.mindhub.homebanking.configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class AuthorizationForUser extends WebSecurityConfigurerAdapter {
/*Utiliza un WebSecurity para crear el proxy de cadena de filtro que realiza la seguridad basada en web
 para Spring Security. Luego exporta los frijoles necesarios. Se pueden realizar personalizaciones
 en WebSecurity extendiendo WebSecurityConfigurerAdapter y exponiéndolo como una configuración o implementando
 WebSecurityConfigurer y exponiéndolo como una configuración. Esta configuración se importa cuando se utiliza
 EnableWebSecurity.*/

@Override
protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/clients/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/clients/current/**").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET, "/api/loans/**").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.PATCH, "/api/cards").hasAuthority("CLIENT")
                .antMatchers("/web/**").hasAnyAuthority("ADMIN","CLIENT")
                .antMatchers("/rest/**","/api/admin/**").hasAuthority("ADMIN");
        http.formLogin()
                .usernameParameter("emailAddress")
                .passwordParameter("password")
                .loginPage("/api/login").permitAll();
        //una ruta
        http.logout().logoutUrl("/api/logout");
        http.csrf().disable();

    // turn off checking for CSRF tokens

    http.csrf().disable();

    //disabling frameOptions so h2-console can be accessed

    http.headers().frameOptions().disable();

    // if user is not authenticated, just send an authentication failure response

    http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

    // if login is successful, just clear the flags asking for authentication

    http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

    // if login fails, just send an authentication failure response

    http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

    // if logout is successful, just send a success response

    http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

}
    private void clearAuthenticationAttributes(HttpServletRequest request) {
    //para que no solicite la authentication del clients
        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }
}
