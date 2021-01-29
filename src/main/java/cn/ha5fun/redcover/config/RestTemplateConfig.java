package cn.ha5fun.redcover.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author chen
 * @Company ha5fun.com
 * @Description restTemplate
 * @Date 2021/1/27 4:27 下午
 * @Version 1.0.0
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

