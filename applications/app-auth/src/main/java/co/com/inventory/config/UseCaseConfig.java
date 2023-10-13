package co.com.inventory.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.inventory.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UsC$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Repository$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Updater$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Bus$")
        },
        useDefaultFilters = false)
public class UseCaseConfig {
}
