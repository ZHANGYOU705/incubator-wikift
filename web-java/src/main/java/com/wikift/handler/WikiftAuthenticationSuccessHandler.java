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
package com.wikift.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wikift.cache.WikiftCacheManager;
import com.wikift.common.HttpTemplate;
import com.wikift.common.WikiftConstant;
import com.wikift.entity.RemoteServerEntity;
import com.wikift.support.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WikiftSuccessHandler <br/>
 * 描述 : WikiftSuccessHandler <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-08 上午10:33 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Component(value = "wikiftAuthenticationSuccessHandler")
public class WikiftAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private HttpTemplate httpTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private WikiftCacheManager cacheManager;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String accessPath = request.getServletPath();
        HttpSession session = request.getSession();
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        // 抽取restapi用户数据
        Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("Authorization", "Bearer " + cacheManager.get(WikiftConstant.CACHE_AUTHENTICATION_TOKEN).getValue());
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        String path = remoteServer.fullPath() + "user/info/" + authentication.getPrincipal();
        String userJson = httpTemplate.getRemoteResponseToString(path, headers);
        JSONObject userInfo = JSON.parseObject(userJson).getJSONObject("data");
        // 将授权用户信息抽取到session中
        session.setAttribute("userInfo", userInfo);
        if (accessPath.equals(WikiftConstant.COMMON_MENU_AUTHENTICATION)) {
            response.sendRedirect(WikiftConstant.COMMON_WHITE_LIST_ROOT);
        } else {
            response.sendRedirect(accessPath);
        }
    }

}
