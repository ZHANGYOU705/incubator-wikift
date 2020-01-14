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
import com.wikift.common.HttpTemplate;
import com.wikift.common.JsonTemplate;
import com.wikift.common.PageTemplate;
import com.wikift.common.WikiftConstant;
import com.wikift.entity.RemoteServerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * IndexController <br/>
 * 描述 : IndexController <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-03 上午10:33 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Controller
@RequestMapping(value = "index", method = RequestMethod.GET)
public class IndexController {

    @Autowired
    private HttpTemplate httpTemplate;

    @Autowired
    private PageTemplate pageTemplate;

    @Autowired
    private JsonTemplate jsonTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private WikiftCacheManager cacheManager;

    @RequestMapping(value = {"", "/{page}/{size}"}, method = RequestMethod.GET)
    public String index(Model model,
                        @PathVariable(value = "page", required = false) Integer page,
                        @PathVariable(value = "size", required = false) Integer size) {
        PageTemplate.PageEntity pageEntity = pageTemplate.checkPageParam(page, size);
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String latest = httpTemplate.getRemoteResponseToString(remoteServer.fullPath() +
                "public/article/list?orderBy=NATIVE_CREATE_TIME&page=" + (pageEntity.getPage() - 1)
                + "&size=" + pageEntity.getSize());
        model.addAttribute("latest", jsonTemplate.getJsonObject(latest));
        return WikiftConstant.TEMPLATE_INDEX_AND_ROOT_PAGE_PATH;
    }

    @RequestMapping(value = {"navbar/latest"}, method = RequestMethod.GET)
    public String navbarLatest() {
        return "redirect:/index";
    }

    @RequestMapping(value = {"navbar/hottest", "navbar/hottest/{page}/{size}"}, method = RequestMethod.GET)
    public String navbarHottest(Model model,
                                @PathVariable(value = "page", required = false) Integer page,
                                @PathVariable(value = "size", required = false) Integer size) {
        PageTemplate.PageEntity pageEntity = pageTemplate.checkPageParam(page, size);
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String hottest = httpTemplate.getRemoteResponseToString(remoteServer.fullPath() +
                "public/article/list?orderBy=VIEW&page=" + (pageEntity.getPage() - 1)
                + "&size=" + pageEntity.getSize());
        model.addAttribute("hottest", jsonTemplate.getJsonObject(hottest));
        return WikiftConstant.TEMPLATE_INDEX_NAVBAR_AND_ROOT_PAGE_PATH + "hottest";
    }

    @RequestMapping(value = {"navbar/recommend", "navbar/recommend/{page}/{size}"}, method = RequestMethod.GET)
    public String navbarRecommend(Model model,
                                  @PathVariable(value = "page", required = false) Integer page,
                                  @PathVariable(value = "size", required = false) Integer size) {
        PageTemplate.PageEntity pageEntity = pageTemplate.checkPageParam(page, size);
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String recommend = httpTemplate.getRemoteResponseToString(remoteServer.fullPath() +
                "public/article/list?orderBy=FABULOU&page=" + (pageEntity.getPage() - 1)
                + "&size=" + pageEntity.getSize());
        model.addAttribute("recommend", jsonTemplate.getJsonObject(recommend));
        return WikiftConstant.TEMPLATE_INDEX_NAVBAR_AND_ROOT_PAGE_PATH + "recommend";
    }

    @PreAuthorize(value = "hasRole('USER')")
    @RequestMapping(value = {"navbar/forme/{userId}", "navbar/forme/{page}/{size}/{userId}"}, method = RequestMethod.GET)
    public String navbarForme(Model model,
                              @PathVariable(value = "userId", required = true) Integer userId,
                              @PathVariable(value = "page", required = false) Integer page,
                              @PathVariable(value = "size", required = false) Integer size) {
        PageTemplate.PageEntity pageEntity = pageTemplate.checkPageParam(page, size);
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String result = httpTemplate.getRemoteResponseToString(
                remoteServer.fullPath() +
                        String.format("article/my?page=%s&size=%s&userId=%s", (pageEntity.getPage() - 1), pageEntity.getSize(), userId),
                httpTemplate.getHeaders());
        System.out.println(result);
        model.addAttribute("forme", jsonTemplate.getJsonObject(result));
        return WikiftConstant.TEMPLATE_INDEX_NAVBAR_AND_ROOT_PAGE_PATH + "forme";
    }

}