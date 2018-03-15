package nuance.base.IN;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import nuance.base.INClientInterface;

@Configuration
@ConfigurationProperties(prefix = "in.debit")
public class DebitINClientInterface extends INClientInterface {

}
