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
package com.wikift.entity;

import com.wikift.common.WikiftConstant;
import lombok.Data;
import lombok.ToString;
import org.springframework.core.env.Environment;

/**
 * RemoteServerEntity <br/>
 * 描述 : RemoteServerEntity <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-03 下午1:49 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Data
@ToString
public class RemoteServerEntity {

    private String address;
    private String apiVersion;
    private String apiPath;
    private String apiGrantType;
    private String apiClientId;
    private Environment environment;

    public RemoteServerEntity(Environment environment) {
        this.address = environment.getProperty(WikiftConstant.CONFIG_SERVER_PREFIX + ".url");
        this.apiPath = environment.getProperty(WikiftConstant.CONFIG_SERVER_PREFIX + ".api.root");
        this.apiVersion = environment.getProperty(WikiftConstant.CONFIG_SERVER_PREFIX + ".api.version");
        this.apiGrantType = environment.getProperty(WikiftConstant.CONFIG_SERVER_PREFIX + ".api.grant-type");
        this.apiClientId = environment.getProperty(WikiftConstant.CONFIG_SERVER_PREFIX + ".api.client-id");
        this.environment = environment;
    }

    public String fullPath() {
        return address + apiPath + apiVersion;
    }

    public String oauthPath() {
        return address + environment.getProperty(WikiftConstant.CONFIG_SERVER_PREFIX + ".oauth");
    }

}