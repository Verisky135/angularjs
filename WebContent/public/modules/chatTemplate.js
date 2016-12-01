angular.module("chatModule").run(["$templateCache", function($templateCache) {$templateCache.put("../templates/chat_box_template_irontec.html","<div ng-show=\"visible\" class=\"row chat-window col-xs-5 col-md-3 {{vm2.theme}}\" ng-class=\"{minimized: vm2.isHidden}\">\r\n    <div class=\"col-xs-12 col-md-12\">\r\n        <div class=\"panel\">\r\n            <div class=\"panel-heading chat-top-bar\">\r\n                <div class=\"col-md-8 col-xs-8\">\r\n                    <h3 class=\"panel-title\">GAN</h3>\r\n                </div>\r\n                <div class=\"col-md-4 col-xs-4 window-actions\" style=\"text-align: right;\">\r\n                    <span class=\"fa\" ng-class=\"vm2.chatButtonClass\" ng-click=\"vm2.toggle()\"></span>\r\n         <span class=\"fa fa-close\" ng-click=\"vm2.close()\"></span>        </div>\r\n            </div>\r\n            <div class=\"panel-body\" ng-style=\"vm2.panelStyle\">\r\n                <div class=\"msg-container-base\">\r\n                    <div class=\"row msg-container\" ng-repeat=\"message in vm2.messages\" ng-init=\"selfAuthored = vm2.myUserId == message.fromUserId\">\r\n                        <div class=\"col-md-12 col-xs-12\">\r\n                            <div class=\"chat-msg\" ng-class=\"{\'chat-msg-sent\': selfAuthored, \'chat-msg-recieved\': !selfAuthored}\">\r\n                                <span class=\"hide\">myUserId:{{vm2.myUserId}}</span>\r\n                                <img ng-if=\"message.imageUrl\" class=\"profile\" ng-class=\"{\'pull-right\': selfAuthored, \'pull-left\': !selfAuthored}\" ng-src=\"{{message.imageUrl}}\" />\r\n                                <p>{{message.content}}</p>\r\n                                <div class=\"chat-msg-author\">\r\n                                    <strong>{{message.username}}</strong>&nbsp;\r\n                                    <span class=\"date\">{{message.date|date:hh:mm:a}}</span>\r\n                                </div>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <div class=\"chat-bottom-bar\">\r\n                    <form style=\"display:inherit\" ng-submit=\"vm2.submitFunction()\">\r\n                        <div class=\"input-group\" >\r\n                            <input type=\"text\" class=\"form-control input-sm chat-input\" placeholder=\"{{vm2.inputPlaceholderText}}\" ng-model=\"vm2.writingMessage\" />\r\n                        <span class=\"input-group-btn\">\r\n                            <input type=\"submit\" class=\"btn btn-sm chat-submit-button\" value=\"{{vm2.submitButtonText}}\" />\r\n                        </span>\r\n                        </div>\r\n                    </form>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</div>\r\n");}]);