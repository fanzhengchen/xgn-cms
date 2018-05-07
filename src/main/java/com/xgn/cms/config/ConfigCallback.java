package com.xgn.cms.config;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by shade on 2017/4/6.
 */
@Configuration
@Scope("singleton")
@DisconfUpdateService(classes = {AppConf.class})
public class ConfigCallback implements IDisconfUpdate {

    private static Logger logger = LoggerFactory.getLogger(ConfigCallback.class);

    @Override
    public void reload() throws Exception {
        logger.info("reload");
    }
}
