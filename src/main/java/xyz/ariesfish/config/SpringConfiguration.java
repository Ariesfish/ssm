package xyz.ariesfish.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * same as bean.xml
 */
// @Configuration // can remove because used by AnnotationConfigApplicationContext
@ComponentScan(basePackages = "xyz.ariesfish")
@Import(JdbcConfiguration.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {

}
