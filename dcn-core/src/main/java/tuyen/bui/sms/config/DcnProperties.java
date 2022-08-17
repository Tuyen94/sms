package tuyen.bui.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "dcn")
public class DcnProperties {

    private String defaultPort;
    private List<RegistrationProperties> registrations;
}
