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

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.wikift.common.JwtTemplate;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

/**
 * WikiftCommonConfig <br/>
 * 描述 : WikiftCommonConfig <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-03 下午4:07 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Configuration
public class WikiftCommonConfig {

    @Bean
    @Description(value = "wikift gson instance")
    public Gson gson() {
        return new Gson();
    }

    @Bean
    @Description(value = "wikift httpclien instance")
    public CloseableHttpClient client() {
        return HttpClients.createDefault();
    }

    @Bean
    @Description(value = "wikift jwt parse instance")
    public JwtTemplate jwtTemplate() {
        return JwtTemplate.build();
    }

}