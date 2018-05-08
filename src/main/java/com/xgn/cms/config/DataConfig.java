package com.xgn.cms.config;


import com.alibaba.druid.pool.DruidDataSource;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
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

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPassword(commonConfig.getRedisPassword());
        factory.setDatabase(commonConfig.getRedisDatabase());
        factory.setHostName(commonConfig.getRedisHost());
        factory.setPort(commonConfig.getRedisPort());
        factory.setUsePool(true);
        return factory;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        Config config = commonConfig;
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(config.getRabbitmqAddress());
        connectionFactory.setUsername(config.getRabbitmqUsername());
        connectionFactory.setPassword(config.getRabbitmqPassword());
        connectionFactory.setVirtualHost(config.getRabbitmqVirtualHost());

        return connectionFactory;
    }

}
