var username = prompt("Username : ");

while(username == "" || username == null){
	username = prompt("Username : ");		
}

var webSocket = new WebSocket("ws://localhost:8080/SimpleChatroomRealtime/chatServerEndpoint");
webSocket.onmessage = function processMessage(message) {
	document.getElementById("ta-display").append("\n" + message.data);
}

function sendText() {
	let inputText = document.getElementById("ip-text").value;

	var contentSend = "{ \"message\" : \"" + inputText + "\", " + 
	 					 "\"username\" : \"" + username + "\" " + "}";	
	webSocket.send(contentSend);
	document.getElementById("ip-text").value = "";
}

// if enter = send message
function keypress(evt) {
	if(evt.keyCode === 13){
		sendText();
	} 
}