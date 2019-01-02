package ua.configurtion.spring.project.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ua.configurtion.spring.project.repository")
@ComponentScan(basePackages = {"ua.configurtion.spring.project.service.*"})
@PropertySource("classpath:application.properties")
public class DatabaseConfig {
//З ресурсів будемо підставляти наші параметри
	
	@Autowired
	Environment env;//він вміє читати @PropertySource("classpath:application.properties")
	
	@Bean
	public DataSource getDataSource() throws PropertyVetoException {//тут створюємо підключення з базою і підставляємо з application.properties дані
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getProperty("spring.datasource.driver"));
		dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUser(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));

		dataSource.setMaxPoolSize(Integer.parseInt(env.getRequiredProperty("spring.datasource.c3p0.maximum-pool-size")));
		dataSource.setMinPoolSize(Integer.parseInt(env.getRequiredProperty("spring.datasource.c3p0.minimum-pool-size")));
		dataSource.setMaxIdleTime(Integer.parseInt(env.getRequiredProperty("spring.datasource.c3p0.connection-timeout")));
		//dataSource.setMaxStatements(Integer.parseInt(env.getRequiredProperty("spring.datasource.c3p0.maximum-statements")));
		return dataSource;
	}
		
		@Bean(name = "entityManagerFactory")
		public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() throws PropertyVetoException {//буде передавати дані до бази зберігати і тд
			LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
			emf.setDataSource(getDataSource());
			emf.setPackagesToScan(new String[] {"ua.configurtion.spring.project.entity"});
			emf.setPersistenceUnitName("spring-jpa-unit");
			emf.setJpaVendorAdapter(getHibernateAdapter());
			Properties jpaProperties = new Properties();
			jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
			jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
			jpaProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
			jpaProperties.put("hibernate.format_sql", env.getProperty("spring.jpa.format_sql"));

			emf.setJpaProperties(jpaProperties);
			return emf;
		}
		
		@Bean(name = "transactionManager")// даному випадку транзакції не будуть записуватися якщо наприклад пропадає інтернет і цей ТранзактіонМенеджер буде їх перехоплювати
		@Autowired
		public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf) throws NamingException {
			JpaTransactionManager jpaTransaction = new JpaTransactionManager();
			jpaTransaction.setEntityManagerFactory(emf);
			return jpaTransaction;
		}

		@Bean
		public JpaVendorAdapter getHibernateAdapter() {
			return new HibernateJpaVendorAdapter();
		}
}
