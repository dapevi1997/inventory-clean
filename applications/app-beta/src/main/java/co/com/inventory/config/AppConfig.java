package co.com.inventory.config;

import co.com.inventory.mapper.JSONMapper;
import co.com.inventory.mapper.JSONMapperImpl;
import co.com.inventory.mysql.config.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

@Configuration
public class AppConfig {

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository repository) {
        return username -> repository
                .findByUserEmail(username)
                .cast(UserDetails.class)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")));
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(
            ReactiveUserDetailsService userDetailsService) {
        UserDetailsRepositoryReactiveAuthenticationManager authManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authManager.setPasswordEncoder(passwordEncoder());
        return authManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JSONMapper jsonMapper(){
        return new JSONMapperImpl();
    }


}
