package com.xgn.cms.config;


import com.baidu.disconf.client.common.annotations.DisconfFile;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author : Liw
 * @description :
 * @date : 2018/3/23 11:02
 */
@Configuration
@Scope("singleton")
@DisconfFile(filename = "application.properties")
public class AppConf {

}