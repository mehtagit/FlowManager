package nmss.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
/* @PropertySource("Config.properties") */
public class AppConfig {

	/*
	 * @Value("#{'${ucip.live.circle}'.split(',')}") public List<String>
	 * liveCircles;
	 */

	@Value("${cdr.server.ip}")
	public String cdr_ip;

	@Value("${cdr.server.port}")
	public int cdr_port;

	@Value("${provisning.server.ip}")
	public String provisningIp;

	@Value("${provisning.server.port}")
	public int provisningPort;

	@Value("${provisning.activate.packet}")
	public String activatePacket;

	@Value("${provisning.deactivate.packet}")
	public String deactivatePacket;

	@Value("${client.token.url}")
	public String tokenUrl;

	@Value("${client.balance.url}")
	public String balanceUrl;

	@Value("${client.debit.url}")
	public String debitUrl;

	@Value("${service.thread.pool.size}")
	public int serviceThreadPoolSize;

	@Value("${fulfilment.request.table}")
	public String fulfilmentRequestTable;

	@Value("${fulfilment.request.select.logic}")
	public String selectRequestLogic;
	
	@Value("${fulfilment.request.RetryAck.logic}")
	public String RetryAckRequestLogic;

	@Value("${db.connection.type}")
	public String dbConnectionType;

	@Value("${transaction.tps}")
	public int transactionTps;

	@Value("${response.url.readtimeout}")
	public int responseUrlReadtimeout;

	@Value("${response.url.connecttimeOut}")
	public int responseUrlConnecttimeout;

	@Value("${isStub}")
	public boolean isStub;

	@Value("${retry.ack}")
	public boolean retryAck;

	@Value("${responseIP}")
	public String responseIP;

	@Value("${responsePORT}")
	public int responsePORT;

	public boolean isPullingFromDB = true;
	
	@Bean
    public MessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18/users", "i18/errormsg");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("file:Config.properties");
        messageSource.setCacheSeconds(60*10);
        return messageSource;
    }
	
}
