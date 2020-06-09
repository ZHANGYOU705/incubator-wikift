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
package com.wikift.controller.rest;

import com.wikift.common.HttpTemplate;
import com.wikift.common.JsonTemplate;
import com.wikift.common.WikiftConstant;
import com.wikift.entity.RemoteServerEntity;
import com.wikift.param.user.UserParam;
import com.wikift.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * WriterController <br/>
 * 描述 : WriterController <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-08 下午2:43 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@RestController
@RequestMapping(value = "authentication")
public class AuthenticationRestController {

    @Autowired
    private HttpTemplate httpTemplate;

    @Autowired
    private JsonTemplate jsonTemplate;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/register/do", method = RequestMethod.POST)
    public ResponseResult articleWriterDo(@RequestBody UserParam param) {
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String registerApiPath = remoteServer.fullPath() + "public/user/register";
        String result = httpTemplate.postRemoteResponseToStringNoHeader(registerApiPath, jsonTemplate.toJson(param));
        return ResponseResult.getResponse(result, WikiftConstant.COMMON_MENU_AUTHENTICATION);
    }

}