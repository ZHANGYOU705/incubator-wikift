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
package com.wikift.provider;

import com.google.gson.Gson;
import com.wikift.cache.WikiftCache;
import com.wikift.cache.WikiftCacheManager;
import com.wikift.common.JwtTemplate;
import com.wikift.common.WikiftConstant;
import com.wikift.entity.BadCredentialsEntity;
import com.wikift.entity.JwtTokenEntity;
import com.wikift.entity.RemoteServerEntity;
import com.wikift.entity.SuccessCredentialsEntity;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * WikiftAuthenticationProvider <br/>
 * 描述 : WikiftAuthenticationProvider <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-03 下午1:43 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Component(value = "wikiftAuthenticationProvider")
public class WikiftAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private Environment environment;

    @Autowired
    private Gson gson;

    @Autowired
    private CloseableHttpClient client;

    @Autowired
    private JwtTemplate jwtTemplate;

    @Autowired
    private WikiftCacheManager cacheManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String encoding = environment.getProperty(WikiftConstant.CONFIG_WEB_PREFIX + ".encoding");
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        // 使用远程oauth2服务进行登录验证账号
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String param = "?username=" + username + "&password=" + password
                + "&grant_type=" + remoteServer.getApiGrantType()
                + "&client_id=" + remoteServer.getApiClientId();
        HttpPost post = new HttpPost(remoteServer.oauthPath() + param);
        post.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.setHeader("Authorization", "Basic d2lraWZ0LWNsaWVudDp3aWtpZnQtd2Vi");
        try {
            CloseableHttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            if (!ObjectUtils.isEmpty(entity)) {
                String result = EntityUtils.toString(entity, encoding);
                // 授权失败
                BadCredentialsEntity badCredentials = gson.fromJson(result, BadCredentialsEntity.class);
                if (ObjectUtils.isEmpty(badCredentials.getError())) {
                    // 授权成功
                    SuccessCredentialsEntity successCredentials = gson.fromJson(result, SuccessCredentialsEntity.class);
                    JwtTokenEntity jwtToken = (JwtTokenEntity) jwtTemplate.decodedJwtTokenBody(successCredentials.getAccess_token(), JwtTokenEntity.class);
                    List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
                    Arrays.asList(jwtToken.getAuthorities()).forEach(grant -> {
                        grantedAuthoritys.add(new SimpleGrantedAuthority("ROLE_" + grant));
                    });
                    WikiftCache cache = new WikiftCache();
                    cache.setKey(WikiftConstant.CACHE_AUTHENTICATION_TOKEN);
                    cache.setValue(successCredentials.getAccess_token());
                    cache.setTimeOut(0);
                    cacheManager.put(WikiftConstant.CACHE_AUTHENTICATION_TOKEN, cache);
                    return new UsernamePasswordAuthenticationToken(username, password, grantedAuthoritys);
                } else {
                    System.out.println(gson.toJson(result));
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}