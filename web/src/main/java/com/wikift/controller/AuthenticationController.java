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
package com.wikift.controller;

import com.wikift.cache.WikiftCacheManager;
import com.wikift.common.WikiftConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AuthenticationController <br/>
 * 描述 : AuthenticationController <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-03 上午10:50 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Controller
@RequestMapping(value = "authentication")
public class AuthenticationController {

    @Autowired
    private WikiftCacheManager cacheManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String authenticationLogin() {
        return WikiftConstant.TEMPLATE_AUTHENTICATION_LOGIN_PAGE_PATH + "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String authenticationRegister() {
        return WikiftConstant.TEMPLATE_AUTHENTICATION_LOGIN_PAGE_PATH + "register";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String authenticationLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!ObjectUtils.isEmpty(authentication)) {
            // 从spring security中退出当前登录的用户
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        // 清空当前用户的session数据
        request.getSession().invalidate();
        // 清空当前缓冲的用户token数据
        cacheManager.clear(WikiftConstant.CACHE_AUTHENTICATION_TOKEN);
        return "redirect:" + WikiftConstant.COMMON_MENU_AUTHENTICATION + "?logout";
    }

}