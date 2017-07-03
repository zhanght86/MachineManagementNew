package com.springframework.orm;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement 
public class MachineManagementDataSource {

	// DataSource Configuration
	private String driverClass = "com.mysql.cj.jdbc.Driver";
	private String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/MachineManagement?autoReconnect=true";
	private String user = "root";
	private String password = "root12345";
	// C3P0 Configuration
	private String dataSourceName = "MachineManagementDataSourceBean";
	private int minPoolSize = 10;
	private int maxPoolSize = 100;
	private int maxIdleTime = 10;
	private int acquireIncrement = 10;
	private int maxStatements = 10;
	private int initialPoolSize = 10;
	private int idleConnectionTestPeriod = 10;
	private int acquireRetryAttempts = 10;
	private boolean breakAfterAcquireFailure = true;
	private boolean testConnectionOnCheckin = true;
	private boolean testConnectionOnCheckout = true;
	private String preferredTestQuery="SELECT 1";
	
	// Hibernate Configuration
	private String[] packageToScan =new String[]{"com.las.MachineManagement.Bean"};
	private String hibernate_hbm2ddl_auto = "none";
	private String hibernate_dialect = "org.hibernate.dialect.MySQL5Dialect";
	private String hibernate_show_sql = "true";
	private String hibernate_format_sql = "false";
	private String hibernate_use_sql_comments = "true";

	 @Bean(name = "MachineManagementDataSourceBean" ,destroyMethod="close")
	 public DataSource dataSource() {
	 ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
	 try {
	 comboPooledDataSource.setDataSourceName(this.dataSourceName);
	 comboPooledDataSource.setPassword(this.password);
	 comboPooledDataSource.setUser(this.user);
	 comboPooledDataSource.setJdbcUrl(this.jdbcUrl);
	 comboPooledDataSource.setDriverClass(this.driverClass);
	 comboPooledDataSource.setMinPoolSize(this.minPoolSize);
	 comboPooledDataSource.setMaxPoolSize(this.maxPoolSize);
	 comboPooledDataSource.setMaxIdleTime(this.maxIdleTime);
	 comboPooledDataSource.setAcquireIncrement(this.acquireIncrement);
	 comboPooledDataSource.setMaxStatements(this.maxStatements);
	 comboPooledDataSource.setInitialPoolSize(this.initialPoolSize);
	 comboPooledDataSource.setIdleConnectionTestPeriod(this.idleConnectionTestPeriod);
	 comboPooledDataSource.setAcquireRetryAttempts(this.acquireRetryAttempts);
	 comboPooledDataSource.setBreakAfterAcquireFailure(this.breakAfterAcquireFailure);
	 comboPooledDataSource.setTestConnectionOnCheckout(this.testConnectionOnCheckout);
	
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 return comboPooledDataSource;
	 }

	/**
	 * Hibernate 属性配置
	 * @return
	 */
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		try {
			properties.setProperty("hibernate.hbm2ddl.auto", this.hibernate_hbm2ddl_auto);
			properties.setProperty("hibernate.dialect", this.hibernate_dialect);
			properties.setProperty("hibernate.show_sql", this.hibernate_show_sql);
			properties.setProperty("hibernate.format_sql", this.hibernate_format_sql);
			properties.setProperty("hibernate.use_sql_comments", this.hibernate_use_sql_comments);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return properties;
	}

	@Bean(name = "sessionFactoryMachineManagement")
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		try {
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(this.packageToScan);
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory.getObject();
	}

	
	@Resource(name="sessionFactoryMachineManagement")
	@Bean(name = "transactionManagerMachineManagement")
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactoryMachineManagement) {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactoryMachineManagement);
		return hibernateTransactionManager;
	}

}