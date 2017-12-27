package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SecurityConfig.class })
@ComponentScan(basePackages = { "service", "entities", "dao", "config" })
public class RootConfig {

}
