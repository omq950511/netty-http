<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>主页</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <span>${username}</span>
        <button onclick="openWebSocket()">openWebSocket</button>
        <button onclick="closeWebSocket()">closeWebSocket</button>
        <input id="timeText" type="text"/>
    </body>
    <script type="text/javascript" src="script/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        var websocket;
        var wsAddress = "${wsAddress}";
        function openWebSocket(){
            if (window.WebSocket) {
                websocket = new WebSocket(wsAddress);
                websocket.onopen = function (event){
                    var obj = {
                        code:1
                    }
                    sendSocketMsg(JSON.stringify(obj))
                }
                websocket.onmessage = function (event){
                    var data = event.data;
                    $("#timeText").val(data);
                }
                websocket.onclose = function (event) {
                    alert("websocket连接关闭");
                };
            }
        }
        function closeWebSocket() {
            websocket.close();
        }
        function sendSocketMsg(message) {
            if (!window.WebSocket) {
                return;
            }
            if(websocket){
                if (websocket.readyState == WebSocket.OPEN) {
                    websocket.send(message);
                } else {
                    websocket.close();
                }
            }
        }
    </script>
</html>