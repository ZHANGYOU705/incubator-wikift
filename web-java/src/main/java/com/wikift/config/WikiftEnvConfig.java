/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wikift.config;

import com.wikift.common.WikiftConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * WikiftEnvConfig <br/>
 * 描述 : WikiftEnvConfig <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-12 上午11:20 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Configuration
public class WikiftEnvConfig {

    @Resource
    private Environment environment;

    @Resource
    private void configureSystemConfig(ThymeleafViewResolver viewResolver) {
        Map<String, Object> config = new HashMap<>();
        config.put("websiteName", environment.getProperty(WikiftConstant.CONFIG_SYSTEM_PREFIX + "name"));
        config.put("websiteVersion", environment.getProperty(WikiftConstant.CONFIG_SYSTEM_PREFIX + "version"));
        config.put("websiteBeta", environment.getProperty(WikiftConstant.CONFIG_SYSTEM_PREFIX + "beta"));
        viewResolver.setStaticVariables(config);
    }

}