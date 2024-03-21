<%-- 
    Document   : index
    Created on : Mar 6, 2024, 1:14:57 AM
    Author     : DoanManhTai
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>CodePen - Material Messaging App Concept</title>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css'><link rel="stylesheet" href="css/styleChat.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    </head>
    <body>
        <!-- partial:index.partial.html -->
    <body>
        <div class="container">
            <div class="row">
                <div style="width: 30%; height: 700px; overflow-y: auto;" id="discussions" class="discussions">
                    <c:forEach var="item" items="${requestScope.userMsg}">
                        <a style="color: ${requestScope.receiver == item.id ? 'black' : 'grey'};"  href="chat?receiver=${item.id}">
                            <div class="discussion message-active">
                                <div class="photo" style="background-image: url(https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80);">
                                    <div class="online"></div>
                                </div>
                                <div class="desc-contact">
                                    <p class="name">${item.fullname}</p>
                                    <!--<p class="message">9 pm at the bar if possible 😳</p>-->
                                </div>
                                <div class="timer">12 sec</div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
                <section class="chat" style="width: 60%">
                    <div class="header-chat">
                        <i class="icon fa fa-user-o" aria-hidden="true"></i>
                        <p class="name">${requestScope.receiverName}</p>
                        <i class="icon clickable fa fa-ellipsis-h right" aria-hidden="true"></i>
                    </div>
                    <div style="width: 100%; height: 530px; overflow-y: auto;" id="messages-chat" class="messages-chat">
                    </div>
                    <div class="footer-chat">
                        <i class="icon fa fa-smile-o clickable" style="font-size:25pt;" aria-hidden="true"></i>
                        <input type="text" id="message" class="write-message" placeholder="Type your message here"></input>
                        <button onclick="sendMessage()" class="icon send fa fa-paper-plane-o clickable" aria-hidden="true"></button>
                    </div>
                </section>
            </div>
        </div>
    </body>
    <!-- partial -->
    <script  src="./script.js"></script>
    <script>
                            $(document).ready(function () {
                                var postData = {
                                    receiver: ${requestScope.receiver}
                                };
                                console.log(postData);
                                $.ajax({
                                    url: 'http://localhost:9999/SWP391_Gr1/chat',
                                    data: JSON.stringify(postData),
                                    contentType: 'application/json',
                                    type: 'POST',
                                    dataType: 'json',
                                    success: function (data) {
                                        console.log('data', data);

                                        var messagesChatDiv = $('#messages-chat');
                                        messagesChatDiv.empty(); // Xóa nội dung hiện tại của messages-chat
                                        $.each(data, function (index, item) {
                                            var messageDiv = $('<div class="message"></div>');

                                            if (item.senderId == ${sessionScope.account.getId()}) {
                                                messageDiv.addClass("text-only");
                                                messageDiv.html('<div class="response"><p class="text">' + item.content + '</p></div><br>');
                                            } else {
                                                messageDiv.html('<br><div class="photo" style="background-image: url(https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80);"><div class="online"></div></div><p class="text">' + item.content + '</p>');
                                                messageDiv.append('<br><p class="response-time time">' + item.timestamp + '</p><br>');
                                            }

                                            $('#messages-chat').append(messageDiv);
                                        });
                                    },
                                    error: function (xhr, status, error) {
                                        console.log('Error:', error);
                                    }
                                });
                            });
    </script>
    <script type="text/javascript">
        var url = "ws://localhost:9999/SWP391_Gr1/chatRoomServer/${sessionScope.account.getId()}/${requestScope.receiver}/${requestScope.productid}";

        console.log(url);

        var webSocket = new WebSocket(url);

        webSocket.onopen = function (event) {
            console.log("WebSocket connection opened.");
            if (${requestScope.productid} != "0") {
                var message = "productid:" + ${requestScope.productid};
                webSocket.send(message);
            }
        };

        webSocket.onmessage = function (event) {
            var message = JSON.parse(event.data);
            console.log(message);

            var messageDiv = $('<div class="message"></div>');

            if (message.id != null && message.fullname != null) {

                console.log("object test:" + message);
                // Create discussion element
                var discussionDiv = $('<div class="discussion message-active"></div>');
                discussionDiv.attr('id', 'discussion-' + message.id);

                var discussionLink = $('<a href="chat?receiver=' + message.id + '"></a>');

                // Create photo element
                var photoDiv = $('<div class="photo"></div>');
                photoDiv.css('background-image', 'url(' + message.avatar + ')');
                photoDiv.append('<div class="online"></div>');

                // Create desc-contact element
                var descContactDiv = $('<div class="desc-contact"></div>');
                descContactDiv.append('<p class="name">' + message.fullname + '</p>');

                // Create timer element
                var timerDiv = $('<div class="timer">12 sec</div>');

                // Append elements to discussionDiv
                discussionLink.append(photoDiv);
                discussionLink.append(descContactDiv);
                discussionLink.append(timerDiv);
                discussionDiv.append(discussionLink);

                // Append discussionDiv to discussions section
                $('#discussions').prepend(discussionDiv);
            } else {
                console.log(message);

                if (message.senderId == ${sessionScope.account.getId()}) {
                    messageDiv.addClass("text-only");
                    messageDiv.html('<div class="response"><p class="text">' + message.content + '</p></div>');
                } else if (message.senderId != ${sessionScope.account.getId()} && message.senderId == ${requestScope.receiver}) {
                    messageDiv.html('<div class="photo" style="background-image: url(https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80);"><div class="online"></div></div><p class="text">' + message.content + '</p>');
                    messageDiv.append('<br><p class="response-time time">' + message.timestamp + '</p><br>');
                }

                $('#messages-chat').append(messageDiv);
            }
        };

        webSocket.onclose = function (event) {
            console.log("WebSocket connection closed.");
        };

        webSocket.onerror = function (error) {
            console.error("WebSocket error:", error);
        };


        function sendMessage() {
            var message = document.getElementById("message").value;
            webSocket.send(message);
            document.getElementById("message").value = "";
        }
    </script>

    <!--    UserMessageList-->
    <!--    <script type="text/javascript">
            var url = "ws://localhost:9999/SWP391_Gr1/UserListChatEndpoint";
            if (${sessionScope.account.getId()} != null) {
                url = url + "/" + "${sessionScope.account.getId()}";
            } else {
                url = url + "/" + 0;
            }
            if (${requestScope.receiver} != null) {
                url = url + "/" + ${requestScope.receiver};
            } else {
                url = url + "/" + 0;
            }
            console.log(url);
            var webSocket = new WebSocket(url);
    
            webSocket.onopen = function (event) {
                console.log("WebSocket connection opened.");
            };
    
            webSocket.onmessage = function (event) {
                var message = JSON.parse(event.data);
                console.log(message);
                // Create discussion element
                var discussionDiv = $('<div class="discussion message-active"></div>');
                discussionDiv.attr('id', 'discussion-' + message.id);
    
                var discussionLink = $('<a href="chat?receiver=' + message.id + '"></a>');
    
    
                // Create photo element
                var photoDiv = $('<div class="photo"></div>');
                photoDiv.css('background-image', 'url(' + message.avatar + ')');
                photoDiv.append('<div class="online"></div>');
    
                // Create desc-contact element
                var descContactDiv = $('<div class="desc-contact"></div>');
                descContactDiv.append('<p class="name">' + message.fullname + '</p>');
    
                // Create timer element
                var timerDiv = $('<div class="timer">12 sec</div>');
    
                // Append elements to discussionDiv
                discussionLink.append(photoDiv);
                discussionLink.append(descContactDiv);
                discussionLink.append(timerDiv);
                discussionDiv.append(discussionLink);
    
                // Append discussionDiv to discussions section
                $('#discussions').prepend(discussionDiv);
            };
    
            webSocket.onclose = function (event) {
                console.log("WebSocket connection closed.");
            };
    
            webSocket.onerror = function (error) {
                console.error("WebSocket error:", error);
            };
        </script>-->
</body>
</html>
