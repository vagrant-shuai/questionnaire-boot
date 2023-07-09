package com.xs.api.web.controller;

import com.xs.common.envconfig.service.SysEnvConfigService;
import com.xs.common.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xs.common.envconfig.constant.ConfigConstants.SYSTEM_INFO_CONFIG;


/**
 * 公开接口
 *
 * @author xs
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/public/")
public class PublicController {
    private final SysEnvConfigService configService;


    /***
     * 获取系统信息配置
     * @return 系统信息配置
     */
    @GetMapping("systemInfoConfig")
    public Result<String> getSystemInfoConfig() {
        return Result.success(configService.getValueByKey(SYSTEM_INFO_CONFIG));
    }


}
