package nuance.base;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

import nuance.base.pojo.Request;
import nuance.nmss.pojos.crbt.PackDetails;
import nuance.util.SqlUtility;

@Configuration
// @ConfigurationProperties("foo")
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	@NotNull
	@NotBlank
	private String dbConnectionType;

	@NotNull
	@NotBlank
	private String dbConnectionUrl;
	@NotNull
	@NotBlank
	private String dbConnectionUser;
	@NotNull
	private String dbConnectionPass;
	@NotNull
	@NotBlank
	private String dbConnectionDriver;

	@Min(1)
	@Max(10)
	private int dbMinConnections;

	@Min(11)
	@Max(50)
	private int dbMaxConnections;

	private boolean isPullingFromDb = true;

	@Min(20)
	@Max(1000)
	private int serviceThreadPoolSize;

	@Min(1)
	@Max(1000)
	private int transactionTps;

	@NotNull
	@NotBlank
	private String fulfilmentRequestTable;
	
	@NotNull
	@NotBlank
	private String fulfilmentRequestSelectLogic;
	
	@Bean
	@ConditionalOnMissingBean(name = "mapOfFallBack")
	public java.util.concurrent.ConcurrentHashMap<String, List<PackDetails>> mapOfFallBack() {
		return new java.util.concurrent.ConcurrentHashMap();
	}
	
	@Bean
	@ConditionalOnMissingBean(name = "gson")
	public Gson gson() {
		return new Gson();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(BasicDataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbConnectionDriver);
		dataSource.setUrl(dbConnectionUrl);
		dataSource.setUsername(dbConnectionUser);
		dataSource.setPassword(dbConnectionPass);
		dataSource.setMaxActive(dbMaxConnections);
		dataSource.setInitialSize(dbMinConnections);
		dataSource.setValidationQuery("select NOW()");

		dataSource.setTestOnBorrow(true);

		return dataSource;
	}

	/*
	 * @Bean public DataSourceInitializer dataSourceInitializer(DataSource
	 * dataSource) { DataSourceInitializer dataSourceInitializer = new
	 * DataSourceInitializer(); dataSourceInitializer.setDataSource(dataSource);
	 * ResourceDatabasePopulator databasePopulator = new
	 * ResourceDatabasePopulator(); databasePopulator.addScript(new
	 * ClassPathResource("data.sql"));
	 * dataSourceInitializer.setDatabasePopulator(databasePopulator);
	 * dataSourceInitializer.setEnabled(Boolean.parseBoolean(initDatabase));
	 * return dataSourceInitializer; }
	 */

	@Bean
	@ConditionalOnMissingBean(name = "requestQueue")
	public BlockingQueue<Request> requestQueue() {
		return new java.util.concurrent.LinkedBlockingQueue<Request>();
	}

	@Bean
	@ConditionalOnMissingBean(name = "transactionQueue")
	public BlockingQueue<Transaction> transactionQueue() {
		return new java.util.concurrent.LinkedBlockingQueue<Transaction>();
	}

	@Bean
	@ConditionalOnMissingBean(name = "ackQueue")
	public BlockingQueue<String> ackQueue() {
		return new java.util.concurrent.LinkedBlockingQueue<String>();
	}
	
	@Bean
	@ConditionalOnMissingBean(name = "clientSocket")
	public java.net.DatagramSocket clientSocket() {
		java.net.DatagramSocket clientSocket = null;
		try {
			clientSocket = new java.net.DatagramSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientSocket;
	}

	@Bean
	@ConditionalOnMissingBean(name = "sqlUtility")
	public SqlUtility SqlUtility() {
		try {
			return new SqlUtility(dbConnectionType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getDbConnectionType() {
		return dbConnectionType;
	}

	public int getServiceThreadPoolSize() {
		return serviceThreadPoolSize;
	}

	public int getTransactionTps() {
		return transactionTps;
	}

	public boolean isPullingFromDb() {
		return isPullingFromDb;
	}

	public String getDbConnectionUrl() {
		return dbConnectionUrl;
	}

	public void setDbConnectionUrl(String dbConnectionUrl) {
		this.dbConnectionUrl = dbConnectionUrl;
	}

	public String getDbConnectionUser() {
		return dbConnectionUser;
	}

	public void setDbConnectionUser(String dbConnectionUser) {
		this.dbConnectionUser = dbConnectionUser;
	}

	public String getDbConnectionPass() {
		return dbConnectionPass;
	}

	public void setDbConnectionPass(String dbConnectionPass) {
		this.dbConnectionPass = dbConnectionPass;
	}

	public String getDbConnectionDriver() {
		return dbConnectionDriver;
	}

	public void setDbConnectionDriver(String dbConnectionDriver) {
		this.dbConnectionDriver = dbConnectionDriver;
	}

	public int getDbMinConnections() {
		return dbMinConnections;
	}

	public void setDbMinConnections(int dbMinConnections) {
		this.dbMinConnections = dbMinConnections;
	}

	public int getDbMaxConnections() {
		return dbMaxConnections;
	}

	public void setDbMaxConnections(int dbMaxConnections) {
		this.dbMaxConnections = dbMaxConnections;
	}

	public void setDbConnectionType(String dbConnectionType) {
		this.dbConnectionType = dbConnectionType;
	}

	public void setPullingFromDb(boolean isPullingFromDb) {
		this.isPullingFromDb = isPullingFromDb;
	}

	public void setServiceThreadPoolSize(int serviceThreadPoolSize) {
		this.serviceThreadPoolSize = serviceThreadPoolSize;
	}

	public void setTransactionTps(int transactionTps) {
		this.transactionTps = transactionTps;
	}

	public String getFulfilmentRequestTable() {
		return fulfilmentRequestTable;
	}

	public void setFulfilmentRequestTable(String fulfilmentRequestTable) {
		this.fulfilmentRequestTable = fulfilmentRequestTable;
	}

	public String getFulfilmentRequestSelectLogic() {
		return fulfilmentRequestSelectLogic;
	}

	public void setFulfilmentRequestSelectLogic(String fulfilmentRequestSelectLogic) {
		this.fulfilmentRequestSelectLogic = fulfilmentRequestSelectLogic;
	}

}
