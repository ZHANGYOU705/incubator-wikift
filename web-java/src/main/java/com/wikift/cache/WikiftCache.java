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

/**
 * WikiftCache <br/>
 * 描述 : WikiftCache <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-08 上午11:11 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
public class WikiftCache {

    private String key; // 缓冲标识
    private Object value; // 缓冲值
    private long firstTime = System.currentTimeMillis(); // 缓冲时间
    private long timeOut = 0; // 失效时间, 默认不过期
    private long lastRefer = System.currentTimeMillis(); // 最后刷新时间

    public WikiftCache() {
    }

    public WikiftCache(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public WikiftCache(String key, Object value, long timeOut) {
        this.key = key;
        this.value = value;
        this.timeOut = timeOut;
    }

    public WikiftCache(String key, Object value, long timeOut, long lastRefer) {
        this.key = key;
        this.value = value;
        this.timeOut = timeOut;
        this.lastRefer = lastRefer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(long firstTime) {
        this.firstTime = firstTime;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public long getLastRefer() {
        return lastRefer;
    }

    public void setLastRefer(long lastRefer) {
        this.lastRefer = lastRefer;
    }

    @Override
    public String toString() {
        return "WikiftCache{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", firstTime=" + firstTime +
                ", timeOut=" + timeOut +
                ", lastRefer=" + lastRefer +
                '}';
    }

}