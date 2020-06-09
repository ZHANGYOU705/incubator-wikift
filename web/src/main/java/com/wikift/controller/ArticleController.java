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

import com.wikift.common.HttpTemplate;
import com.wikift.common.JsonTemplate;
import com.wikift.common.WikiftConstant;
import com.wikift.common.utils.PageAndSortUtils;
import com.wikift.model.article.ArticleEntity;
import com.wikift.support.service.article.ArticleService;
import com.wikift.support.service.comment.CommentService;
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

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String info(Model model, @PathVariable(value = "id") Integer id) {
        ArticleEntity entity = this.articleService.getArticle(Long.valueOf(id));
        model.addAttribute("details", entity);
        // 文章评论信息
        model.addAttribute("comments", commentService.getAllCommentByArticle(entity, PageAndSortUtils.getPageAndSortAndCreateTimeByDESC(0, 20)));
        return WikiftConstant.TEMPLATE_ARTICLE_PAGE_PATH + "details";
    }


}
