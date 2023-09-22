package ru.kartel.conditionalapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kartel.conditionalapp.systemprofile.DevProfile;
import ru.kartel.conditionalapp.systemprofile.ProductionProfile;
import ru.kartel.conditionalapp.systemprofile.SystemProfile;

@Configuration
public class Config {

    @Bean
    @ConditionalOnProperty(prefix = "profile", name = "dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "profile", name = "dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}


