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
'use strict';
$(document).ready(function () {
    /**
     * 表单数据检查:当有数据时,输入框进入激活状态
     */
    if ($('.form-group--float')[0]) {
        $('.form-group--float').each(function () {
            var p = $(this).find('.form-control').val()
            if (!p.length == 0) {
                $(this).find('.form-control').addClass('form-control--active');
            }
        });
        $('body').on('blur', '.form-group--float .form-control', function () {
            var i = $(this).val();
            if (i.length == 0) {
                $(this).removeClass('form-control--active');
            }
            else {
                $(this).addClass('form-control--active');
            }
        });
    }
    /**
     * tooltip
     */
    if($('[data-toggle="tooltip"]')[0]) {
        $('[data-toggle="tooltip"]').tooltip();
    }
});