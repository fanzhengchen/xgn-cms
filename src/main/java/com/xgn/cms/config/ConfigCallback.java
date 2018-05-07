package com.xgn.cms.config;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by shade on 2017/4/6.
 */
@Configuration
@Scope("singleton")
@Slf4j
@DisconfUpdateService(classes = {Config.class,AppConf.class})
public class ConfigCallback implements IDisconfUpdate {

    @Override
    public void reload() throws Exception {
        log.info("reload");
    }
}
