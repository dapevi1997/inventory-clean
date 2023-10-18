package co.com.inventory.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig  {
    static {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(Throwable.class);
    }

    @Bean
    public GroupedOpenApi authOpenApi(@Value("${springdoc.version}") String appVersion) {
        String[] paths = { "/**" };

        return GroupedOpenApi.builder().
                group("auth")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Auth API").version(appVersion)))
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi streamOpenApi(@Value("${springdoc.version}") String appVersion) {
        String[] paths = { "/**" };
        String[] packagedToMatch = { "co.com.inventory.api" };
        return GroupedOpenApi.builder().group("x-stream")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Stream API").version(appVersion)))
                .pathsToMatch(paths).packagesToScan(packagedToMatch)
                .build();
    }

}
