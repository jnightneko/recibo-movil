package org.wmdb.recibo.system;

import java.util.List;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@RequiredArgsConstructor
@ConfigurationProperties("application.cors")
public class CORSHost {
    private String host;
    private List<String> methods;
}
