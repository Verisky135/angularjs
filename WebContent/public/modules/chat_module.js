//
//var chat_module = angular.module('chatModule', []);
//
//chat_module.directive('chatDirective', ['$timeout', CatalogChat]);
//
//function CatalogChat($timeout) {
//	
//	var directive = {
//		restrict: 'EA',
//		replace: true,
//		templateUrl:'../templates/chat_box_template.html',
//		scope: {
//			userTarget: '@',
//			submitFunction: '&'
//		},
//		link: function(scope, element, attrs){
//			console.log('User target: ' + scope.userTarget);
//		}
//	};
//	
//	return directive;
//	
//}

(function() {
	'use strict';

	angular.module('chatModule', []);
	angular.module('chatModule').directive('chatDirective', ['$timeout', CatalogChat]);

	function CatalogChat($timeout) {
		var directive = {
			restrict: 'EA',
			templateUrl: '../templates/chat_box_template_irontec.html',
			replace: true,
			scope: {
				messages: '=',
				username: '=',
				myUserId: '=',
				inputPlaceholderText: '@',
				submitButtonText: '@',
				title: '=',
				theme: '@',
				submitFunction: '&',
				visible: '=',
				infiniteScroll: '&',
                expandOnNew: '='
			},
			link: link,
			controller: ChatCtrl,
			controllerAs: 'vm2'
		};

		function link(scope, element) {
			if (!scope.inputPlaceholderText) {
				scope.inputPlaceholderText = 'Write your message here...';

			}

			if (!scope.submitButtonText || scope.submitButtonText === '') {
				scope.submitButtonText = 'Send';
			}

			if (!scope.title) {
				scope.title = 'Chat';
			}

			scope.$msgContainer = $('.msg-container-base'); // BS angular $el jQuery lite won't work for scrolling
			scope.$chatInput = $(element).find('.chat-input');

			var elWindow = scope.$msgContainer[0];
			scope.$msgContainer.bind('scroll', _.throttle(function() {
				var scrollHeight = elWindow.scrollHeight;
				if (elWindow.scrollTop <= 10) {
					scope.historyLoading = true; // disable jump to bottom
					scope.$apply(scope.infiniteScroll);
					$timeout(function() {
						scope.historyLoading = false;
						if (scrollHeight !== elWindow.scrollHeight) // don't scroll down if nothing new added
							scope.$msgContainer.scrollTop(360); // scroll down for loading 4 messages
					}, 150);
				}
			}, 300));
		}

		return directive;
	}

	ChatCtrl.$inject = ['$scope', '$timeout'];

	function ChatCtrl($scope, $timeout) {
		var vm2 = this;

        vm2.isHidden = false;
		vm2.messages = $scope.messages;
		vm2.username = $scope.username;
		vm2.myUserId = $scope.myUserId;
		vm2.inputPlaceholderText = $scope.inputPlaceholderText;
		vm2.submitButtonText = $scope.submitButtonText;
		vm2.title = $scope.title;
		vm2.theme = 'chat-th-' + $scope.theme;
		vm2.writingMessage = '';
		vm2.panelStyle = {'display': 'block'};
		vm2.chatButtonClass= 'fa-angle-double-down icon_minim';

		vm2.toggle = toggle;
		vm2.close = close;
		vm2.submitFunction = submitFunction;

		function submitFunction() {
			$scope.submitFunction()(vm2.writingMessage, vm2.username);
			vm2.writingMessage = '';
			scrollToBottom();
		}

		$scope.$watch('visible', function() { // make sure scroll to bottom on visibility change w/ history items
			scrollToBottom();
			$timeout(function() {
				$scope.$chatInput.focus();
			}, 250);
		});
		$scope.$watch('messages.length', function() {
			if (!$scope.historyLoading) scrollToBottom(); // don't scrollToBottom if just loading history
            if ($scope.expandOnNew && vm2.isHidden) {
                toggle();
            }
		});

		function scrollToBottom() {
			$timeout(function() { // use $timeout so it runs after digest so new height will be included
				$scope.$msgContainer.scrollTop($scope.$msgContainer[0].scrollHeight);
			}, 200, false);
		}

		function close() {
			// close the chat box
			$scope.visible = false;
			
			// clear the list of message
			$scope.messages.length = 0;
			console.log("scope.messages\n");
			console.log($scope.messages);
			console.log("vm2.messages\n");
			console.log(vm2.messages);
		}

		function toggle() {
			if(vm2.isHidden) {
				vm2.chatButtonClass = 'fa-angle-double-down icon_minim';
				vm2.panelStyle = {'display': 'block'};
				vm2.isHidden = false;
				scrollToBottom();
			} else {
				vm2.chatButtonClass = 'fa-expand icon_minim';
				vm2.panelStyle = {'display': 'none'};
				vm2.isHidden = true;
			}
		}
	}
})();
