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
var WikiftCommonNotify = function () {

    var initNotify = function (notify, config) {
        if (config !== 'undefiend' || config !== '' || config !== null) {
            config = initConfig(notify, config);
        }
        $.notify(notify, config);
    };

    /**
     * init properties
     * @returns {{}}
     */
    var initConfig = function (notify, config) {
        if (notify instanceof WikiftNotifyEntity) {
            var options = {};
            options.icon = notify.icon;
            options.title = notify.title;
            options.message = notify.message;
            options.type = config.type;
            // options.template = initTemplate(notify);
            return options;
        }
        throw Error('wikift notify config is error!');
    };

    /**
     * 初始化模板
     */
    var initTemplate = function (notify) {
        return '<div data-notify="container" class="alert alert-dismissible alert-{0} alert--notify" role="alert">' +
            '<span data-notify="icon"></span> ' +
            '<span data-notify="title">{notify.title}</span> ' +
            '<span data-notify="message">{notify.message}</span>' +
            '<div class="progress" data-notify="progressbar">' +
            '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
            '</div>' +
            '<a href="{3}" target="{4}" data-notify="url"></a>' +
            '<button type="button" aria-hidden="true" data-notify="dismiss" class="alert--notify__close">Close</button>' +
            '</div>';
    };

    return {
        initNotify: initNotify
    }

}();