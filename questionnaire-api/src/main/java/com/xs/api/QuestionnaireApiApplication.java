package com.xs.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author vagrant_shuai
 * @date 2023-07-09 20:40
 * @description 描述
 */
@SpringBootApplication
@EnableAsync
@EnableCaching
@ComponentScan("com.xs.*")
public class QuestionnaireApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireApiApplication.class,args);
        System.out.println("问卷系统服务启动成功！");
    }
}
