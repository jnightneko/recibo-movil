package org.wmdb.recibo.system;

import java.util.Arrays;
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
        String[] hosts = corsHost.getHost().split(" ");
        for (int i = 0; i < hosts.length; i++) {
            hosts[i] = hosts[i].trim();
        }
        
        String[] methods = corsHost.getMethods().toArray(String[]::new);
        registry.addMapping("/**")
                .allowedMethods(methods)
                .allowedHeaders("*")
                .allowedOrigins(hosts);
        
        System.out.println("Hosts: " + Arrays.toString(hosts));
        System.out.println("Methods: " + Arrays.toString(methods));
    }
}
