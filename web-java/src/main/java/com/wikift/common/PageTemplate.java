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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * PageTemplate <br/>
 * 描述 : PageTemplate <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-04 下午5:55 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Component
public class PageTemplate {

    public PageEntity checkPageParam(Integer page, Integer size) {
        if (ObjectUtils.isEmpty(page)) {
            page = 1;
        }
        if (ObjectUtils.isEmpty(size)) {
            size = 10;
        }
        return new PageEntity(page, size);
    }

    @Data
    @ToString
    @AllArgsConstructor
    public class PageEntity {
        private Integer page;
        private Integer size;
    }

}