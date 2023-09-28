package co.com.inventory.config;

import co.com.inventory.mapper.JSONMapper;
import co.com.inventory.mapper.JSONMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericConfig {
    @Bean
    public JSONMapper jsonMapper(){
        return new JSONMapperImpl();
    }
}
