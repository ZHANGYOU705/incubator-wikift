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
package com.wikift.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * WikiftCacheManager <br/>
 * 描述 : WikiftCacheManager <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-08 上午11:14 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Component(value = "wikiftCacheManager")
public interface WikiftCacheManager {

    /**
     * 存入缓存
     *
     * @param key   缓冲标识
     * @param cache 缓冲数据
     */
    void put(String key, WikiftCache cache);

    /**
     * 存入缓存
     *
     * @param key     缓冲标识
     * @param cache   缓冲数据
     * @param timeOut 超时时间
     */
    void put(String key, Object cache, long timeOut);

    /**
     * 获取对应缓存
     *
     * @param key 缓冲标识
     * @return 缓冲数据
     */
    WikiftCache get(String key);

    /**
     * 获取所有缓存
     *
     * @return 所有缓存
     */
    Map<String, WikiftCache> getAll();

    /**
     * 判断是否在缓存中
     *
     * @param key 缓冲标识
     * @return 是否存在缓冲中
     */
    boolean contains(String key);

    /**
     * 清除所有缓存
     */
    void clearAll();

    /**
     * 清除对应缓存
     *
     * @param key 缓冲标识
     */
    void clear(String key);

    /**
     * 缓存是否超时失效
     *
     * @param key 缓冲标识
     * @return 是否超时
     */
    boolean timeOut(String key);

    /**
     * 获取所有key
     *
     * @return 所有缓冲标识
     */
    Set<String> getAllKeys();

    /**
     * 刷新缓冲
     *
     * @param key   缓冲标识
     * @param cache 缓冲数据
     */
    void refer(String key, Object cache);

}