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
package com.wikift.filter;

import com.wikift.cache.WikiftCache;
import com.wikift.cache.WikiftCacheManager;
import com.wikift.cache.WikiftCacheManagerImpl;
import com.wikift.common.WikiftConstant;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * OauthTokenFilter <br/>
 * 描述 : OauthTokenFilter <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-09 上午11:19 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
public class OauthTokenFilter implements Filter {

    // 不用过滤的地址
    private static Set<String> whiteList = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        whiteList.add(WikiftConstant.COMMON_MENU_AUTHENTICATION);
        whiteList.add(WikiftConstant.COMMON_WHITE_LIST_INDEX_ALL);
        whiteList.add(WikiftConstant.COMMON_WHITE_LIST_ROOT);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        WikiftCacheManager cacheManager = new WikiftCacheManagerImpl();
        WikiftCache cache = cacheManager.get(WikiftConstant.CACHE_AUTHENTICATION_TOKEN);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!whiteList.contains(request.getServletPath())) {
            if (ObjectUtils.isEmpty(cache)) {
                response.sendRedirect(WikiftConstant.COMMON_MENU_AUTHENTICATION);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}