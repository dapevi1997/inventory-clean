package co.com.inventory.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    static {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(Throwable.class);
    }

    @Bean
    public GroupedOpenApi betaOpenApi(@Value("${springdoc.version}") String appVersion) {
        String[] paths = { "/**" };

        return GroupedOpenApi.builder().
                group("beta")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Beta API").version(appVersion)))
                .pathsToMatch(paths)
                .build();
    }
}