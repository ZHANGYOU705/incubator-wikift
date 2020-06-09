/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function WikiftRemoteUserEntity(id, username, password, repassword, avatar, aliasName, signature, email, active, lock) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.repassword = repassword;
    this.avatar = avatar;
    this.aliasName = aliasName;
    this.signature = signature;
    this.email = email;
    this.active = active;
    this.lock = lock;
}

var WikiftRemoteUserEntityUtils = function () {

    var getUserByJson = function getUserByJson(json) {
        if (WikiftUtilsJson.isJson(json)) {
            var userInfo = new WikiftRemoteUserEntity();
            userInfo.id = json.id;
            userInfo.username = json.username;
            userInfo.avatar = json.avatar;
            userInfo.aliasName = json.aliasName;
            userInfo.signature = json.signature;
            userInfo.email = json.email;
            userInfo.active = json.active;
            userInfo.lock = json.lock;
            return userInfo;
        }
        return null;
    };

    return {
        getUserByJson: getUserByJson
    }

}();