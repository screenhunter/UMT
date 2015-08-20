
var DEBUG = true;

var guestModule = angular.module('guestApp', []);

guestModule.controller('SubscribeController', ['$http', '$scope', '$window', 
	function($http, $scope, $window) {
	
		var subscribeCtrl = this;
		subscribeCtrl.subscribing = false;
		subscribeCtrl.errorMessage = null;
		subscribeCtrl.successMessage = null;
		if (DEBUG)
			console.log("SubscribeController loaded.");
			
		subscribeCtrl.error = function(message, details){
			
			subscribeCtrl.subscribing = false;
			subscribeCtrl.errorMessage = message;
			subscribeCtrl.errorDetails = details;
			
		};

		subscribeCtrl.success = function(message, details){
			
			subscribeCtrl.subscribing = false;
			subscribeCtrl.successMessage = message;
			subscribeCtrl.successDetails = details;
			
		};
		
		subscribeCtrl.subscribeCall = function() {
			
			if (DEBUG) console.log("SubscribeController called.");
			subscribeCtrl.subscribing = true;
			data = $scope.subscribedUser;
			subscribeCtrl.error(null, null);
			subscribeCtrl.success("Saving...", null);
			if (DEBUG) console.log("User data: " + JSON.stringify(data));
			
			return $http.post('/guest/subscribeuser', data,
				{responseType:"json"})
				.success(function(data, status) {
					
					if (data !== null) {
						
						subscribeCtrl.success("Success!","Account: " + data.email + " is subscribed.");
						if (DEBUG) console.log("Request succeeded: " + JSON.stringify(data));
						//$window.location.href = "user/user.html";
						
					} else {
						
						if (DEBUG) console.log("ERROR: Request succeeded but with empty data.");
						subscribeCtrl.error("Connection error, try again.", "Empty data returned as result.");
						subscribeCtrl.success(null, null);
						
					}
					
				}).error(function(data, status) {
					
					if (DEBUG) {
						
						console.log("Request failed: " + JSON.stringify(data));
						console.log("   with status: " + JSON.stringify(status));
						
					}
					
					subscribeCtrl.success(null, null);
					
					if (status == 400)
						subscribeCtrl.error("Error when subscribing: " + data.errorMessage);
					else
						subscribeCtrl.error("Connection error, try again.", "Error returned as result: " + status);
					
				});
			
		};
		
		if (DEBUG) console.log("SubscribeController functions loaded.");
	
	}
]);