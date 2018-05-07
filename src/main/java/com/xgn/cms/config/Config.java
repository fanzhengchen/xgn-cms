package com.xgn.cms.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * disconf配置
 */
@Configuration
@Scope("singleton")
@Data
@DisconfFile(filename = "common.properties")
public class Config {

    private Integer serverPort;
    private String dataSourceUrl;
    private String dataSourceUserName;
    private String dataSourcePassword;
    private String driverClassName;
    private String jpaDatabase;
    private String hibernateDialect;
    private Boolean showSql;
    private Boolean generateDll;
    private Boolean openInView;
    private String dllAuto;
    private String namingStrategy;
    private String logLevel;

    private int redisPort;
    private String redisHost;
    private String redisPassword;
    private int redisDatabase;

    private String rabbitmqAddress;
    private String rabbitmqUsername;
    private String rabbitmqPassword;
    private String rabbitmqVirtualHost;
    private int rabbitmqChannelCacheSize;
    private int rabbitmqConcurrentConsumers;
    private int rabbitmqMaxConcurrentConsumers;
    private int rabbitmqPrefetchCount;


    @DisconfFileItem(name = "server.port", associateField = "serverPort")
    public Integer getServerPort() {
        return serverPort;
    }

    @DisconfFileItem(name = "spring.datasource.url", associateField = "dataSourceUrl")
    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    @DisconfFileItem(name = "spring.datasource.username", associateField = "dataSourceUserName")
    public String getDataSourceUserName() {
        return dataSourceUserName;
    }

    @DisconfFileItem(name = "spring.datasource.password", associateField = "dataSourcePassword")
    public String getDataSourcePassword() {
        return dataSourcePassword;
    }

    @DisconfFileItem(name = "spring.datasource.driver-class-name", associateField = "driverClassName")
    public String getDriverClassName() {
        return driverClassName;
    }

    @DisconfFileItem(name = "spring.jpa.database", associateField = "jpaDatabase")
    public String getJpaDatabase() {
        return jpaDatabase;
    }


    @DisconfFileItem(name = "spring.jpa.properties.hibernate.dialect", associateField = "hibernateDialect")
    public String getHibernateDialect() {
        return hibernateDialect;
    }

    @DisconfFileItem(name = "spring.jpa.show-sql", associateField = "showSql")
    public Boolean getShowSql() {
        return showSql;
    }

    @DisconfFileItem(name = "spring.jpa.generate-ddl", associateField = "generateDll")
    public Boolean getGenerateDll() {
        return generateDll;
    }

    @DisconfFileItem(name = "spring.jpa.open-in-view", associateField = "openInView")
    public Boolean getOpenInView() {
        return openInView;
    }

    @DisconfFileItem(name = "spring.jpa.hibernate.ddl-auto", associateField = "dllAuto")
    public String getDllAuto() {
        return dllAuto;
    }

    @DisconfFileItem(name = "spring.jpa.hibernate.naming.physical-strategy", associateField = "namingStrategy")
    public String getNamingStrategy() {
        return namingStrategy;
    }

    @DisconfFileItem(name = "logging.level.com.xgn.cms", associateField = "logLevel")
    public String getLogLevel() {
        return logLevel;
    }

    @DisconfFileItem(name = "spring.redis.port", associateField = "redisPort")
    public int getRedisPort() {
        return redisPort;
    }

    @DisconfFileItem(name = "spring.redis.host", associateField = "redisHost")
    public String getRedisHost() {
        return redisHost;
    }

    @DisconfFileItem(name = "spring.redis.password", associateField = "redisPassword")
    public String getRedisPassword() {
        return redisPassword;
    }

    @DisconfFileItem(name = "spring.redis.database", associateField = "redisDatabase")
    public Integer getRedisDatabase() {
        return redisDatabase;
    }

    @DisconfFileItem(name = "rabbitmq.address", associateField = "rabbitmqAddress")
    public String getRabbitmqAddress() {
        return rabbitmqAddress;
    }

    @DisconfFileItem(name = "rabbitmq.username", associateField = "rabbitmqUsername")
    public String getRabbitmqUsername() {
        return rabbitmqUsername;
    }

    @DisconfFileItem(name = "rabbitmq.password", associateField = "rabbitmqPassword")
    public String getRabbitmqPassword() {
        return rabbitmqPassword;
    }

    @DisconfFileItem(name = "rabbitmq.virtualHost", associateField = "rabbitmqVirtualHost")
    public String getRabbitmqVirtualHost() {
        return rabbitmqVirtualHost;
    }

}
