package com.xs.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

/**
 * 配置类
 *
 * @author xs
 */
public class SpringDocConfig {
    @Bean
    public OpenAPI createApi() {
        return new OpenAPI().info(getInfo());
    }


    public Info getInfo() {
        return new Info()
                // 标题
                .title("问卷 接口文档")
                // 简短描述
                .description("基于OpenAPI 3的API文档生成工具")
                // 指向服务条款的URL地址，必须是URL地址格式
                .termsOfService("http://localhost:8999")
                // 公开的API的联系人信息
                .contact(createCon())
                // API文档的版本信息
                .version("0.1");
    }

    private Contact createCon() {
        return new Contact()
                .email("vagrant_shuai@163.com")
                .name("xs")
                .url("www.xxx.xxx");
    }

}
