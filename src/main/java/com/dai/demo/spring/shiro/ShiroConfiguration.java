package com.dai.demo.spring.shiro;

import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wzdxt on 15/3/27.
 */
@Configuration
public class ShiroConfiguration {
    @Bean
    public DefaultHashService hashService() {
        DefaultHashService bean = new DefaultHashService();
        bean.setHashIterations(200000);
        bean.setHashAlgorithmName("SHA-256");
        bean.setGeneratePublicSalt(true);
        bean.setPrivateSalt(ByteSource.Util.bytes("myVERYSECRETBase64EncodedSalt"));
        return bean;
    }
}
