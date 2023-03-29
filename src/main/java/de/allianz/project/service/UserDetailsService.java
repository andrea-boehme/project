package de.allianz.project.service;

import de.allianz.project.config.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@RequiredArgsConstructor
@Configuration // damit Klasse von Spring gefunden wird
public class UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public InMemoryUserDetailsManager users() {

        UserDetails user = User.builder()
                .username("user")
                //.password(passwordEncoder.encode("userPassword"))
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW") // steht für "password", muss man encoden
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                //.password(passwordEncoder.encode("adminPassword"))
                .password("{noop}admin-password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
