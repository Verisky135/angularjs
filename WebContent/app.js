(function() {
	'use strict';

	angular.module('app', ['chatModule']);

	angular.module('app').controller('Shell', ['$scope', '$http', Shell]);
	
	
	// controller function for Shell
	function Shell($scope, $http) {

		var vm = this;

		/* DATA FOR EXPERIMENT
		vm.messages = [
			{
				'username': 'Matt',
				'content': 'Hi!'
			},
			{
				'username': 'Elisa',
				'content': 'Whats up?'
			},
			{
				'username': 'Matt',
				'content': 'I found this nice AngularJS Directive'
			},
			{
				'username': 'Elisa',
				'content': 'Looks Great!'
			}
		];
		*/
		
		
		vm.messages = [];
		
	
		// AJAX request for sending the message to Chat server		
		
		vm.sendToChatServer = function(rcvr_username, msg) {
			
			console.log("\n");
			console.log("sendToChatServer");
			console.log("----------------");
			console.log("Receiver: " + rcvr_username);
			console.log("Message to send: " + msg);
			console.log("----------------");
			console.log("\n");

			/*
			var params = [{'data' : {
    						'sender_username': localStorage.getItem("username_global"),
    						'receiver_username': rcvr_username.replace(/^\s+|\s+$/g, ''),
    						'message': msg
    						}
						}];
			*/
			
			var params = {
				'sender_username': localStorage.getItem("username_global"),
				'receiver_username': rcvr_username.replace(/^\s+|\s+$/g, ''),
				'message': msg
				};

			
			//console.log("STRINGIFY\n");
			//console.log(JSON.stringify(params));
			
			
			$http({
				method: 'POST',
        		url: 'chat_service?client_purpose=send',
        	    data: JSON.stringify(params),
        	    headers : {'Content-Type': 'application/json'}
			}).then(function(response) {
				// success
				console.log(response);
				console.log("Successfully send message to receiver");
			}, function(response) {
				// fail
				console.log(response);
				console.log("Failed to send message to receiver");
			});
			
			console.log("----------------");
			
		}

		
		vm.visible = false;
		vm.username = 'Matt';
		
		vm.sendMessage = function(message, username) {
			if(message && message !== '' && username) {
			  
				// push the message to the chat box
				vm.messages.push({
					'username': localStorage.getItem("username_global"),
					'content': message
				});
				
				// push the message to the Chat server
				vm.sendToChatServer(vm.username, message);
				
			}
		};
		
		
		// add the received message to the list (by FCM)
		$scope.addMessageByFCM = function(message, username) {
			
			// push the message to the chat box
			vm.messages.push({
				'username': username,
				'content': message
			});
			
			// show the chatbox
			vm.visible = true;
			vm.username = username;
			
		};
		
	}
	
})();
