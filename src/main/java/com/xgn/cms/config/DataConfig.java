package com.xgn.cms.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataConfig {

    @Autowired
    Config commonConfig;

    @Bean(name = "druidDataSource")
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(commonConfig.getDataSourceUrl());
        dataSource.setUsername(commonConfig.getDataSourceUserName());// 用户名
        dataSource.setPassword(commonConfig.getDataSourcePassword());// 密码
        return dataSource;
    }

    @Bean
    public PhysicalNamingStrategy namingStrategy() {
        return new PhysicalNamingStrategyStandardImpl();
    }

}
