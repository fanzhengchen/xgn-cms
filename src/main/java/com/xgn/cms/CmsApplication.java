package com.xgn.cms;


import com.baidu.disconf.client.DisconfMgrBean;
import com.baidu.disconf.client.DisconfMgrBeanSecond;
import com.baidu.disconf.client.addons.properties.ReloadablePropertiesFactoryBean;
import com.xinguangnet.tuchao.axe.cloud.register.EnableDiscoveryRegister;
import com.xinguangnet.tuchao.goodscenter.api.constants.GoodscenterConstants;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;


@EnableJpaRepositories("com.xgn.cms.repository")
@EntityScan("com.xgn.cms.entity")
@SpringBootApplication
@EnableDiscoveryRegister
@EnableCaching
@EnableFeignClients(basePackages = {GoodscenterConstants.API_PACKAGE_NAME})
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }


    @Bean
    public DisconfMgrBean disconfMgrBean() throws IOException {
        DisconfMgrBean disconfMgrBean = new DisconfMgrBean();
        disconfMgrBean.setScanPackage("com.xgn.cms.config");
        return disconfMgrBean;
    }

    @Bean
    public DisconfMgrBeanSecond disconfMgrBeanSecond() throws IOException {
        DisconfMgrBeanSecond disconfMgrBeanSecond = new DisconfMgrBeanSecond();
        disconfMgrBeanSecond.init();
        return disconfMgrBeanSecond;
    }

    @Bean
    public Properties disconfConfig() throws IOException {
        PropertiesFactoryBean pf = new ReloadablePropertiesFactoryBean();
        pf.setLocations(new ClassPathResource("application.properties"));
        return pf.getObject();
    }

    @Bean
    public PropertyPlaceholderConfigurer reloadConfig() throws IOException {
        PropertyPlaceholderConfigurer conf = new PropertyPlaceholderConfigurer();
        conf.setIgnoreResourceNotFound(true);
        conf.setIgnoreUnresolvablePlaceholders(true);
        conf.setPropertiesArray(disconfConfig());
        return conf;
    }
}
