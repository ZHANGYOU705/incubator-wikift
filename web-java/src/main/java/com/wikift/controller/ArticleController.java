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

import com.alibaba.fastjson.JSONObject;
import com.wikift.common.HttpTemplate;
import com.wikift.common.JsonTemplate;
import com.wikift.common.WikiftConstant;
import com.wikift.entity.RemoteServerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ArticleController <br/>
 * 描述 : ArticleController <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-10 下午1:11 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Controller
@RequestMapping(value = "{username}/article", method = RequestMethod.GET)
public class ArticleController {

    @Autowired
    private HttpTemplate httpTemplate;

    @Autowired
    private JsonTemplate jsonTemplate;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String info(Model model,
                       @PathVariable(value = "id") Integer id) {
        String accessInfo = "public/article/info/" + id;
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String result = httpTemplate.getRemoteResponseToString(remoteServer.fullPath() + accessInfo);
        JSONObject details = jsonTemplate.getJsonObject(result);
        model.addAttribute("details", details);
        // 文章评论信息
        Integer detailsId = details.getJSONObject("data").getInteger("id");
        String commentPath = "public/comment/list?articleId=" + detailsId;
        String commentResult = httpTemplate.getRemoteResponseToString(remoteServer.fullPath() + commentPath);
        JSONObject comments = jsonTemplate.getJsonObject(commentResult);
        model.addAttribute("comments", comments);
        return WikiftConstant.TEMPLATE_ARTICLE_PAGE_PATH + "details";
    }


}