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

import com.wikift.cache.WikiftCacheManager;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HttpTemplate <br/>
 * 描述 : HttpTemplate <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-04 上午11:48 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Component
public class HttpTemplate {

    private String default_encoding = "UTF-8";

    @Autowired
    private HttpClient client;

    @Autowired
    private WikiftCacheManager cacheManager;

    private HttpResponse getRemoteResponse(String url, Map<String, String> headers) throws IOException {
        HttpGet get = new HttpGet(url);
        if (!ObjectUtils.isEmpty(headers) && headers.size() > 0) {
            headers.keySet().forEach(v -> {
                get.setHeader(v, headers.get(v));
            });
        }
        return client.execute(get);
    }

    public String getRemoteResponseToString(String url) {
        return this.getRemoteResponseToString(url, default_encoding, null);
    }

    public String getRemoteResponseToString(String url, String encoding) {
        return this.getRemoteResponseToString(url, encoding, null);
    }

    public String getRemoteResponseToString(String url, Map<String, String> headers) {
        return this.getRemoteResponseToString(url, default_encoding, headers);
    }

    public String getRemoteResponseToString(String url, String encoding, Map<String, String> headers) {
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(encoding)) {
            throw new RuntimeException("url and encoding must no null");
        }
        try {
            HttpResponse response = this.getRemoteResponse(url, headers);
            if (!ObjectUtils.isEmpty(response)) {
                return EntityUtils.toString(response.getEntity(), encoding);
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("access url" + url + " error!!!", e);
        }
    }

    private HttpResponse postRemoteResponse(String url, Map<String, String> headers, String data) throws IOException {
        HttpPost post = new HttpPost(url);
        if (!ObjectUtils.isEmpty(headers) && headers.size() > 0) {
            headers.keySet().forEach(v -> {
                post.setHeader(v, headers.get(v));
            });
        }
        // 构建消息实体
        StringEntity entity = new StringEntity(data, Charset.forName(default_encoding));
        entity.setContentEncoding(default_encoding);
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        post.setEntity(entity);
        return client.execute(post);
    }

    public String postRemoteResponseToString(String url, String data) {
        return this.postRemoteResponseToString(url, null, this.getHeaders(), data);
    }

    public String postRemoteResponseToStringNoHeader(String url, String data) {
        return this.postRemoteResponseToString(url, null, null, data);
    }

    public String postRemoteResponseToString(String url, String encoding, Map<String, String> headers, String data) {
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("url and encoding must no null");
        }
        try {
            HttpResponse response = this.postRemoteResponse(url, headers, data);
            if (!ObjectUtils.isEmpty(response)) {
                return EntityUtils.toString(response.getEntity(), encoding);
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("access url" + url + " error!!!", e);
        }
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("Authorization", "Bearer " + cacheManager.get(WikiftConstant.CACHE_AUTHENTICATION_TOKEN).getValue());
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

}