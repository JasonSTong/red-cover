package cn.ha5fun.redcover;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.ha5fun.redcover.mapper")
public class RedCoverApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedCoverApplication.class, args);
    }

}
