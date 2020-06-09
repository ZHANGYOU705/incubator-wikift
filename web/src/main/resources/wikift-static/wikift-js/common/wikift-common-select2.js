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
var WikiftCommonSelect2 = function () {

    var initSelect2 = function (select2, config) {
        if (config !== 'undefiend' || config !== '' || config !== null) {
            config = initConfig(config);
        }
        $(select2.id).select2(config);
    };

    /**
     * init properties
     * @returns {{}}
     */
    var initConfig = function (config) {
        var options = {};
        options.dropdownAutoWidth = config.dropdownAutoWidth;
        options.width = config.width;
        options.dropdownParent = config.dropdownParent;
        return options;
        throw Error('wikift notify config is error!');
    };

    return {
        initSelect2: initSelect2
    }

}();