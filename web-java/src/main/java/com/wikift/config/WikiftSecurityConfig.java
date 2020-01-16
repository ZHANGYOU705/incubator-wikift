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
package com.wikift.config;

import com.wikift.common.WikiftConstant;
import com.wikift.handler.WikiftAuthenticationSuccessHandler;
import com.wikift.provider.WikiftAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * WikiftSecurityConfig <br/>
 * 描述 : WikiftSecurityConfig <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-03 上午10:43 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WikiftSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Autowired
    private WikiftAuthenticationProvider wikiftAuthenticationProvider;

    @Autowired
    private WikiftAuthenticationSuccessHandler wikiftAuthenticationSuccessHandler;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(wikiftAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().
                antMatchers(whitePath()).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage(WikiftConstant.COMMON_MENU_AUTHENTICATION)
                .successHandler(wikiftAuthenticationSuccessHandler).permitAll()
                .and().logout().logoutSuccessUrl(WikiftConstant.COMMON_MENU_AUTHENTICATION).permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers(environment.getProperty(WikiftConstant.CONFIG_WEB_PREFIX + ".static-relative-location"));
    }

    private String[] whitePath() {
        List<String> path = new ArrayList<>();
        path.add(WikiftConstant.COMMON_WHITE_LIST_ROOT);
        path.add(WikiftConstant.COMMON_WHITE_LIST_INDEX_ALL);
        path.add(WikiftConstant.COMMON_WHITE_LIST_ARTICLE_DETAILS);
        path.add(WikiftConstant.COMMON_WHITE_LIST_AUTHENTICATION);
        return path.toArray(new String[path.size()]);
    }

}
