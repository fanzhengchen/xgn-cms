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
    private Boolean dllAuto;
    private String namingStrategy;
    private String logLevel;

    @DisconfFileItem(name = "server.port",associateField = "serverPort")
    public Integer getServerPort() {
        return serverPort;
    }

    @DisconfFileItem(name = "spring.datasource.url",associateField = "dataSourceUrl")
    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    @DisconfFileItem(name = "spring.datasource.username",associateField = "dataSourceUserName")
    public String getDataSourceUserName() {
        return dataSourceUserName;
    }

    @DisconfFileItem(name = "spring.datasource.password",associateField = "dataSourcePassword")
    public String getDataSourcePassword() {
        return dataSourcePassword;
    }

    @DisconfFileItem(name = "spring.datasource.driver-class-name",associateField = "driverClassName")
    public String getDriverClassName() {
        return driverClassName;
    }

    @DisconfFileItem(name = "spring.jpa.database",associateField = "jpaDatabase")
    public String getJpaDatabase() {
        return jpaDatabase;
    }


    @DisconfFileItem(name = "spring.jpa.properties.hibernate.dialect",associateField = "hibernateDialect")
    public String getHibernateDialect() {
        return hibernateDialect;
    }

    @DisconfFileItem(name = "spring.jpa.show-sql",associateField = "showSql")
    public Boolean getShowSql() {
        return showSql;
    }

    @DisconfFileItem(name = "spring.jpa.generate-ddl",associateField = "generateDll")
    public Boolean getGenerateDll() {
        return generateDll;
    }

    @DisconfFileItem(name = "spring.jpa.open-in-view",associateField = "openInView")
    public Boolean getOpenInView() {
        return openInView;
    }

    @DisconfFileItem(name = "spring.jpa.hibernate.ddl-auto",associateField = "allAuto")
    public Boolean getDllAuto() {
        return dllAuto;
    }

    @DisconfFileItem(name = "spring.jpa.hibernate.naming.physical-strategy",associateField = "namingStrategy")
    public String getNamingStrategy() {
        return namingStrategy;
    }

    @DisconfFileItem(name = "logging.level.com.xgn.cms",associateField = "debug")
    public String getLogLevel() {
        return logLevel;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public void setDataSourceUrl(String dataSourceUrl) {
        this.dataSourceUrl = dataSourceUrl;
    }

    public void setDataSourceUserName(String dataSourceUserName) {
        this.dataSourceUserName = dataSourceUserName;
    }

    public void setDataSourcePassword(String dataSourcePassword) {
        this.dataSourcePassword = dataSourcePassword;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setJpaDatabase(String jpaDatabase) {
        this.jpaDatabase = jpaDatabase;
    }

    public void setHibernateDialect(String hibernateDialect) {
        this.hibernateDialect = hibernateDialect;
    }

    public void setShowSql(Boolean showSql) {
        this.showSql = showSql;
    }

    public void setGenerateDll(Boolean generateDll) {
        this.generateDll = generateDll;
    }

    public void setOpenInView(Boolean openInView) {
        this.openInView = openInView;
    }

    public void setDllAuto(Boolean dllAuto) {
        this.dllAuto = dllAuto;
    }

    public void setNamingStrategy(String namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }


//@DisconfFileItem(name = "redis.host", associateField = "redisHost")
    //public String getRedisHost() {
    //    return redisHost;



}
