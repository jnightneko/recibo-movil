package org.wmdb.recibo.system;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {
    
    private final CORSHost corsHost;
        
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(corsHost.getMethods().toArray(String[]::new))
                .allowedHeaders("*")
                .allowedOrigins(corsHost.getHost());
    }
}
