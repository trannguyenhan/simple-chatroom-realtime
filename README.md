# Chatroom realtime with java websocket
The server and client will communicate with each other through websocket, the messages are sent in the form of JSON files. <br /><br />
![](https://docs.microsoft.com/vi-vn/azure/application-gateway/media/application-gateway-websocket/websocket.png)
<br /><br />
Unlike the mechanism of http asking for answers, since websocket is TCP protocol, it will follow the 3-step handshake model. <br />
The standard protocol of websocket is ws:// for the regular standard and wss:// for the secure standard (similar to http:// and https://). So when setting up the link to the server, you will have to use ws://localhost/xxx instead of http://localhost/xxx. <br /><br />
Example : <br />
```
var webSocket = new WebSocket("ws://localhost:8080/SimpleChatroomRealtime/chatServerEndpoint");
```
