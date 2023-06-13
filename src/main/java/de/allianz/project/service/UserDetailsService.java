package de.allianz.project.service;

import de.allianz.project.config.PasswordEncoderConfig;
import de.allianz.project.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@RequiredArgsConstructor
@Configuration // damit Klasse von Spring gefunden wird
public class UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsManager users() {

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("userPassword")).roles("MEMBER")
                //.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW") // steht für "password", muss man encoden
                //.roles("USER")
                .authorities(Role.MEMBER.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("adminPassword")).roles("ADMIN")
                //.password("{noop}admin-password")
                //.roles("USER", "ADMIN")
                .authorities(Role.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails analyst = User.builder()
                .username("analyst")
                .password(passwordEncoder.encode("analystPassword")).roles("ANALYST")
                //.password("{noop}analyst-password")
                .authorities(Role.ANALYST.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(user, admin, analyst);
    }
}
