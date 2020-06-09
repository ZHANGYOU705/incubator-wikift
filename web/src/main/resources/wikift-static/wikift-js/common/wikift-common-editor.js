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
var defaultEditorId = 'wikift-editor';

var WikiftCommonEditor = function () {

    var CODE_EDITOR;

    /**
     * 初始化编辑器
     * @param editor 编辑器信息
     * @param config 编辑器配置
     */
    var initEditor = function (editor, config) {
        if (editor === 'undefiend' || editor === '' || editor === null) {
            editor = new WikiftEditorEntity(defaultEditorId, null, null);
        }
        if (config === 'undefiend' || config === '' || config === null) {
            config = new WikiftEditorConfigEntity('100%', '100%', '/ws/wikift-vender/js/editor/lib/');
        }
        CODE_EDITOR = editormd(editor.id, initConfig(editor, config));
    };

    var initMarkdownToHTML = function (editor, config) {
        if (editor === 'undefiend' || editor === '' || editor === null) {
            editor = new WikiftEditorEntity(defaultEditorId, null, null);
        }
        if (config === 'undefiend' || config === '' || config === null) {
            config = new WikiftEditorConfigEntity('100%', '100%', '/ws/wikift-vender/js/editor/lib/');
        }
        editormd.markdownToHTML(editor.id, initConfig(editor, config));
    };

    /**
     * init properties
     * @returns {{}}
     */
    var initConfig = function (editor, config) {
        if (config instanceof WikiftEditorConfigEntity) {
            var options = {};
            options.path = config.libPath;
            options.width = config.width;
            options.height = config.height;
            options.markdown = editor.markdown;
            options.tocContainer = editor.tocContainer;
            options.mode = editor.mode;
            options.toolbarIcons = editor.toolbarIcons;
            return options;
        }
        throw Error('wikift editor config is error!');
    };

    /**
     * set value
     * @param value
     */
    var setValue = function (value) {
        CODE_EDITOR.setValue(value);
    };

    /**
     * get value
     * @returns {*}
     */
    var getValue = function () {
        return CODE_EDITOR.getValue().trim();
    };

    var clearValue = function () {
        CODE_EDITOR.setValue('');
    };

    var undoValue = function () {
        CODE_EDITOR.undo();
    };

    var redoValue = function () {
        CODE_EDITOR.redo();
    };

    var getEditor = function () {
        return CODE_EDITOR;
    };

    return {
        initEditor: initEditor,
        initMarkdownToHTML: initMarkdownToHTML,
        setValue: setValue,
        getValue: getValue,
        clearValue: clearValue,
        undoValue: undoValue,
        redoValue: redoValue,
        getEditor: getEditor
    }

}();