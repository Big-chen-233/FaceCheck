package xyz.chen.baidu_ai_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = "xyz.chen.baidu_ai_back.*")
@EnableJpaRepositories(basePackages = "xyz.chen.baidu_ai_back.dao")
@EntityScan("xyz.chen.baidu_ai_back.pojo")
@SpringBootApplication
public class BaiduAiBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaiduAiBackApplication.class, args);
    }

}
