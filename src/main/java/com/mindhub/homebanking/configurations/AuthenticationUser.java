package com.mindhub.homebanking.configurations;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationUser extends GlobalAuthenticationConfigurerAdapter {
@Autowired //Enlaza el repositorio de client
ClientService clientService;

public void init(AuthenticationManagerBuilder authenticationUser) throws Exception {

        authenticationUser.userDetailsService(inputName ->{

        Client client = clientService.findClientEmailAddress(inputName);

        if (client != null) {
            if (client.getName().contains("admin")) {
                return new User(client.getEmailAddress(),client.getPassword(),
                //AuthorityUtils.commaSeparatedStringToAuthorityList("CLIENT,ADMIN"));
                AuthorityUtils.createAuthorityList("ADMIN"));
            }else {
                return new User(client.getEmailAddress(),client.getPassword(),
                        //AuthorityUtils.commaSeparatedStringToAuthorityList("CLIENT"));
                AuthorityUtils.createAuthorityList("CLIENT"));
            }
            }else{

                throw new UsernameNotFoundException("User " + inputName + " not found");

            }
        });
}
    @Bean
    public PasswordEncoder/*codificación de contraseñas.*/ passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //Crea un codificador de contraseña de delegación con asignaciones predeterminadas.
    }
}
