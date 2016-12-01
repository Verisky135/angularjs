

// AJAX - Send the FCM token to the Chat Server
function sendTokenAJAX(fetched_token) {
	
	var xmlhttp;
	
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var url = "chat_service";
	xmlhttp.open("POST", url, true);

	//Send the proper header information along with the request
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	xmlhttp.onreadystatechange = function() {
		//Call a function when the state changes.
	    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	
	    	if (xmlhttp.responseText == "200") {
	    		console.log("Successfully saved identity and token on the Chat Server");
	    	} else {
	    		console.log("Failed to save FCM_token and identity on the Chat Server\n");
	    	}
	    	
	    }
	}
	
	var params = "client_fcm=" + fetched_token + "&" +
			"client_username=" + localStorage.getItem("username_global") + "&" +
			"client_purpose=save"; 
	
	xmlhttp.send(params);
	
}



// Firebase
// ********************************************************
/*
	TODO(DEVELOPER): Update Firebase initialization code:
	        1. Go to the Firebase console: https://console.firebase.google.com/
	        2. Choose a Firebase project you've created
	        3. Click "Add Firebase to your web app"
	        4. Replace the following initialization code with the code from the Firebase console:
*/

// START INITIALIZATION CODE
			
	var config = {
		apiKey: "AIzaSyDHONl8MDAMYeWT2wQw90uFSjHP6rK7YOA",
		authDomain: "schedule-city-53a66.firebaseapp.com",
		databaseURL: "https://schedule-city-53a66.firebaseio.com",
		storageBucket: "schedule-city-53a66.appspot.com",
		messagingSenderId: "106996652419"
	};
	
	
	// need to fetch the FCM token
	firebase.initializeApp(config);

	
// END INITIALIZATION CODE

// ********************************************************
	
	// [START get_messaging_object]
	// Retrieve Firebase Messaging object.
	
	const messaging = firebase.messaging();


	// [END get_messaging_object]

	// IDs of divs that display Instance ID token UI or request permission UI.
	//const tokenDivId = 'token_div';
	//const permissionDivId = 'permission_div';

	// [START refresh_token]
	// Callback fired if Instance ID token is updated.
	
	messaging.onTokenRefresh(function() {
		messaging.getToken()
			.then(function(refreshedToken) {
				console.log('Token refreshed.');
				// Indicate that the new Instance ID token has not yet been sent to the
				// app server.
				setTokenSentToServer(false);
				// Send Instance ID token to app server.
				sendTokenToServer(refreshedToken);
				// [START_EXCLUDE]
				// Display new Instance ID token and clear UI of all previous messages.
				resetUI();
				// [END_EXCLUDE]
			})
			.catch(function(err) {
				console.log('Unable to retrieve refreshed token ', err);
				//showToken('Unable to retrieve refreshed token ', err);
			});
	});
	
	// [END refresh_token]
	
	// [START receive_message - CALLBACK FUNCTION]
	// Handle incoming messages. Called when:
	// - a message is received while the app has focus
	// - the user clicks on an app notification created by a service worker
	//   'messaging.setBackgroundMessageHandler' handler.
	
	messaging.onMessage(function(payload) {
		
		// show the received message on the console
		console.log("\n");
		console.log("YOU GOT A NEW MESSAGE!");
		console.log("----------------------");
		console.log("Message received: ", payload);
		console.log("----------------------");
		
		// fetch the sender_username and the message from payload
		/*
		JSON.stringify(payload);
		var parsed_payload = JSON.parse(payload);
		var sender_username_pl = parsed_payload.data.sender_username;
		var message_pl = parsed_payload.data.message;
		*/
		
		var sender_username_pl = payload.data.sender_username;
		var message_pl = payload.data.message;
		
		console.log("SU: " + sender_username_pl);
		console.log("Mpl: " + message_pl);
		
		// add the message to the list of messages
	    var scope = angular.element(document.getElementById('supportFCM')).scope();
	    	scope.$apply(function(){
	    		
	    		// store the sender_username and the message
	    		scope.addMessageByFCM(message_pl, sender_username_pl);
	    		
	    });
	
	});
	
	// [END receive_message]
	
	
	
	// [START get_token]
	
	function resetUI() {
		
		
		console.log("Hi, you are in resetUI() now");
		
		
		//clearMessages();
		//showToken('loading...');
		
		// Get Instance ID token. Initially this makes a network call, once retrieved
		// subsequent calls to getToken will return from cache.
		messaging.getToken()
			.then(function(currentToken) {
				if (currentToken) {
					
					// Show the FCM token to the console
					console.log('FCM token: ' + currentToken);
					
					// Send the token to the Chat Server
					sendTokenToServer(currentToken);
					
					// Show token modification UI
					//updateUIForPushEnabled(currentToken);
				
				} else {
					
					// Show permission request.
					console.log('No Instance ID token available. Request permission to generate one.');
					
					// Show permission UI.
					//updateUIForPushPermissionRequired();
					
					setTokenSentToServer(false);
					
					// Request permission
					requestPermission();
					
				}
			})
			.catch(function(err) {
				console.log("[ERROR - resetUI]\n");
				console.log('An error occurred while retrieving Instance ID token. ', err);
				//showToken('Error retrieving Instance ID token. ', err);
				setTokenSentToServer(false);
			});
	}
	
	// [END get_token]
	
	
	// Show the generated token from FCM on a DIV [OPTIONAL - FOR DEBUGGING PURPOSE]
	function showToken(currentToken) {
		// Show token in console and UI.
	    var tokenElement = document.querySelector('#token');
	    tokenElement.textContent = currentToken;
	}
	
	
	// Send the Instance ID token to your application server, so that it can:
	// - send messages back to this app
	// - subscribe/unsubscribe the token from topics
	function sendTokenToServer(currentToken) {
		if (!isTokenSentToServer()) {
			console.log('Sending token to server...');

			// TODO(developer): Send the current token to your server.
			// Send the token to the chat service
			sendTokenAJAX(currentToken);
			
			setTokenSentToServer(true);
		} else {
			console.log('Token already sent to server so will not send it again unless it changes');
		}
	}
	
	
	// Check whether the FCM token has been already sent to the Chat Server
	function isTokenSentToServer() {
		if (window.localStorage.getItem('sentToServer') == 1) {
			return true;
		}
		return false;
	}
	
	
	// Set the status of the FCM token related to whether it has been already sent to the Chat Server
	function setTokenSentToServer(sent) {
		if (sent) {
			window.localStorage.setItem('sentToServer', 1);
		} else {
			window.localStorage.setItem('sentToServer', 0);
		}
	}


	// Show and Hide the FCM token modification area [OPTIONAL - FOR DEBUGGING PURPOSE]
	function showHideDiv(divId, show) {
		const div = document.querySelector('#' + divId);
		if (show) {
			div.style = "display: visible";
		} else {
			div.style = "display: none";
		}
	}
	
	
	// [START request_permission]
		
	function requestPermission() {
		
		console.log('Requesting permission...');
		
		messaging.requestPermission()
			.then(function() {
				
				console.log('Notification permission granted.');
				
				// TODO(developer): Retrieve an Instance ID token for use with FCM.
				
				// [START_EXCLUDE]
				// In many cases once an app has been granted notification permission, it
				// should update its UI reflecting this.
				
				resetUI();
				
				// [END_EXCLUDE]
			
			})
			.catch(function(err) {
				console.log('Unable to get permission to notify.', err);
			});
	
	}
	
	// [END request_permission]
	
	
	// Delete FCM token [OPTIONAL - FOR DEBUGGING PURPOSE]
	function deleteToken() {
		// Delete Instance ID token.
		// [START delete_token]
		
		messaging.getToken()
			.then(function(currentToken) {
				messaging.deleteToken(currentToken)
					.then(function() {
						console.log('Token deleted.');
						setTokenSentToServer(false);
						// [START_EXCLUDE]
						// Once token is deleted update UI.
						resetUI();
						// [END_EXCLUDE]
					})
					.catch(function(err) {
						console.log('Unable to delete token. ', err);
					});
			})
			.catch(function(err) {
				console.log('Error retrieving Instance ID token. ', err);
				showToken('Error retrieving Instance ID token. ', err);
			});
		
		// [END delete_token]
	}

	
	// Add a message to the messages element [OPTIONAL - FOR DEBUGGING PURPOSE]
	function appendMessage(payload) {
		const messagesElement = document.querySelector('#messages');
		const dataHeaderELement = document.createElement('h5');
		const dataElement = document.createElement('pre');
		dataElement.style = 'overflow-x:hidden;'
		dataHeaderELement.textContent = 'Received message:';
		dataElement.textContent = JSON.stringify(payload, null, 2);
		messagesElement.appendChild(dataHeaderELement);
		messagesElement.appendChild(dataElement);
	}
	
	
	// Clear the messages element of all children [OPTIONAL - FOR DEBUGGING PURPOSE]
	function clearMessages() {
		const messagesElement = document.querySelector('#messages');
		while (messagesElement.hasChildNodes()) {
			messagesElement.removeChild(messagesElement.lastChild);
		}
	}
	
	
	// Show the control board for the generated FCM Token [OPTIONAL - FOR DEBUGGING PURPOSE]
	function updateUIForPushEnabled(currentToken) {
		showHideDiv(tokenDivId, true);
		showHideDiv(permissionDivId, false);
		showToken(currentToken);
	}
	
	// Show the control board for requesting the permission [OPTIONAL - FOR DEBUGGING PURPOSE]
	function updateUIForPushPermissionRequired() {
		showHideDiv(tokenDivId, false);
		showHideDiv(permissionDivId, true);
	}

	
	/** USE THIS IF THE FCM TOKEN MUST BE GENERATED BEFORE LOGGED IN (CODE 0) **/
	
	/*
	// Check the validity of the user access (session)
	if (!ValidateUserAccess()) {
		
		console.log("Invalid access");
		window.location.href = "views/catalog.jsp";

	}

	console.log("Valid access");
	
	// Start the FCM token generation
	resetUI();
	*/
	
	/** END OF (CODE 0) **/


	
	/** USE THIS IF THE FCM TOKEN MUST BE GENERATED AFTER LOGGED IN (CODE 1) **/

	// GET user ID (active) from global variables
	var user_active = localStorage.getItem("username_global");
		
	document.getElementById("user_page_li_catalog").className = "active";
		
	console.log("Catalog page");
	console.log("user_active: " + user_active);
	
	// SET user ID (active)
	//SetUserIDActive(user_active);
	
	// ret_all is a special keyword for retreiving all catalog products
	SetSearchCategory("ret_all");
	SearchAJAX("catalog_products", "ret_all");
	
	// reset the search category
	SetSearchCategory("");

	console.log("localStorage - first_catalog " + localStorage.getItem("first_catalog"));
	
	// Start the FCM token generation (if needed)
	if (localStorage.getItem("first_catalog") == 1) {
		// need to fetch the FCM token
		console.log("resetUI - fetcing a FCM token");
		
		resetUI();
		
		console.log("after-resetUI");
	}
	
	
	// Set the first_catalog status to 0 so that the application will not send a 
	// request to get a FCM token
	localStorage.setItem("first_catalog", 0);
	
	
	/** END OF (CODE 1) **/




	/*
	if ('serviceWorker' in navigator) {  
	    navigator.serviceWorker.register('/service-worker.js')  
	    .then(initialiseState);  
	  } else {  
	    console.warn('Service workers aren\'t supported in this browser.');  
	  }
	*/  
	
	
	/*
	if ('serviceWorker' in navigator) {
		navigator.serviceworker.register('C:\Users\AlbertusK95\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ROOT\firebase-messaging-sw.js')
		.then(function(registration) {
		    messaging = firebase.messaging();
		    messaging.useServiceWorker(registration);
		    //return messaging.getToken();
		
			var user_active = localStorage.getItem("username_global");
			
			document.getElementById("user_page_li_catalog").className = "active";
				
			console.log("Catalog page");
			console.log("user_active: " + user_active);
			
			// SET user ID (active)
			//SetUserIDActive(user_active);
			
			// ret_all is a special keyword for retreiving all catalog products
			SetSearchCategory("ret_all");
			SearchAJAX("catalog_products", "ret_all");
			
			// reset the search category
			SetSearchCategory("");

			console.log("localSTORAGE - first_catalog " + localStorage.getItem("first_catalog"));
			
			// Start the FCM token generation (if needed)
			if (localStorage.getItem("first_catalog") == 1) {
				// need to fetch the FCM token
				console.log("resetUI - fetcing a FCM token");
				
				resetUI();
				
				console.log("after-resetUI");
			}
			
			
			// Set the first_catalog status to 0 so that the application will not send a 
			// request to get a FCM token
			localStorage.setItem("first_catalog", 0);
		
		})
		.then(function(token) {
		    console.log("Registration for firebase messaging SW is failed");
		});
	}
	
	*/