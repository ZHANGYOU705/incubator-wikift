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

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WikiftCacheManagerImpl <br/>
 * 描述 : WikiftCacheManagerImpl <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-08 上午11:21 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
public class WikiftCacheManagerImpl implements WikiftCacheManager {

    // 缓冲存放器, 使用ConcurrentHashMap用于线程安全
    private static Map<String, WikiftCache> cacheContainer = new ConcurrentHashMap<>();

    @Override
    public void put(String key, WikiftCache cache) {
        cacheContainer.put(key, cache);
    }

    @Override
    public void put(String key, Object cache, long timeOut) {
        timeOut = timeOut > 0 ? timeOut : 0;
        cacheContainer.put(key, new WikiftCache(key, cache, timeOut));
    }

    @Override
    public WikiftCache get(String key) {
        if (contains(key)) {
            return cacheContainer.get(key);
        }
        return null;
    }

    @Override
    public Map<String, WikiftCache> getAll() {
        return cacheContainer;
    }

    @Override
    public boolean contains(String key) {
        return cacheContainer.containsKey(key);
    }

    @Override
    public void clearAll() {
        cacheContainer.clear();
    }

    @Override
    public void clear(String key) {
        cacheContainer.remove(key);
    }

    @Override
    public boolean timeOut(String key) {
        if (!contains(key)) {
            // 如果缓冲中没有存在该缓冲数据, 则表示为该数据过期
            return true;
        }
        WikiftCache cache = cacheContainer.get(key);
        if (cache.getTimeOut() == 0 || (System.currentTimeMillis() - cache.getLastRefer()) >= cache.getTimeOut()) {
            return true;
        }
        return false;
    }

    @Override
    public Set<String> getAllKeys() {
        return cacheContainer.keySet();
    }

    @Override
    public void refer(String key, Object cache) {
        if (contains(key) && !timeOut(key)) {
            cacheContainer.put(key, new WikiftCache(key, cache));
        }
    }

}