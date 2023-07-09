package com.xs.api.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.xs.storage.OssStorageConfig;
import com.xs.storage.OssStorageFactory;
import com.xs.storage.enums.OssTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.List;

/**
 * web mvc 配置
 *
 * @author xs
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * html静态资源   js静态资源    css静态资源
     */
    private final List<String> staticResources = Lists.newArrayList("/**/*.html",
            "/**/*.js",
            "/**/*.css",
            "/**/*.woff",
            "/**/*.ttf");

    /**
     * 配置本地文件上传的虚拟路径和静态化的文件生成路径
     * 备注：这是一种图片上传访问图片的方法，实际上也可以使用nginx反向代理访问图片
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        OssStorageConfig config = OssStorageFactory.getConfig();
        if (ObjectUtil.isNotNull(config) && OssStorageFactory.getConfig().getOssType() == OssTypeEnum.LOCAL) {
            // 文件上传
            String uploadFolder = config.getUploadFolder();
            //   未配置路径时 使用jar所在目录作为文件默认存储目录
            if (StrUtil.isBlank(uploadFolder)) {
                ApplicationHome ah = new ApplicationHome(OssStorageFactory.class);
                uploadFolder = ah.getDir().getAbsolutePath();

            }
            uploadFolder = StringUtils.appendIfMissing(uploadFolder, File.separator);
            registry.addResourceHandler(config.getAccessPathPattern())
                    .addResourceLocations("file:" + uploadFolder);
        }

        //这句不要忘了，否则项目默认静态资源映射会失效
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // swagger 配置
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

    }
}
