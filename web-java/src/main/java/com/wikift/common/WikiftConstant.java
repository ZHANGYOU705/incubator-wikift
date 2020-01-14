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
package com.wikift.common;

/**
 * WikiftConstant <br/>
 * 描述 : WikiftConstant <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-03 上午10:35 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
public class WikiftConstant {

    public static String CONFIG_SYSTEM_PREFIX = "wikift.system.";
    public static String CONFIG_WEB_PREFIX = "wikift.web.config";
    public static String CONFIG_SERVER_PREFIX = "wikift.server.config";

    /**
     * 页面模板
     */
    public static String TEMPLATE_INDEX_AND_ROOT_PAGE_PATH = "index";
    public static String TEMPLATE_INDEX_NAVBAR_AND_ROOT_PAGE_PATH = "wikift-index/wikift-index-navbar-";
    public static String TEMPLATE_WRITER_PAGE_PATH = "wikift-writer/wikift-writer-";
    public static String TEMPLATE_ARTICLE_PAGE_PATH = "wikift-article/wikift-article-";
    public static String TEMPLATE_AUTHENTICATION_LOGIN_PAGE_PATH = "wikift-authentication/wikift-authentication-";

    /**
     * 全局导航
     */
    public static String COMMON_MENU_AUTHENTICATION = "/authentication/login";

    /**
     * 全局白名单路径
     */
    public static String COMMON_WHITE_LIST_AUTHENTICATION = "/authentication/**";
    public static String COMMON_WHITE_LIST_INDEX_ALL = "/index/**";
    public static String COMMON_WHITE_LIST_ROOT = "/";
    public static String COMMON_WHITE_LIST_ARTICLE_DETAILS = "/**/article/details/*";

    /**
     * 缓冲常量
     */
    public static String CACHE_AUTHENTICATION_TOKEN = "authenticationToken";

}