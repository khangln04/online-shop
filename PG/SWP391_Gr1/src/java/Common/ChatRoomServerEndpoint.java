package Common;

import DataAccess.AccountDAO;
import DataAccess.MessageDAO;
import DataAccess.ProductDAO;
import Models.Account;
import Models.Message;
import Models.Product;
import com.google.gson.Gson;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DoanManhTai
 */
@ServerEndpoint(value = "/chatRoomServer/{userID}/{receiver}/{productid}")
public class ChatRoomServerEndpoint {

    private static final Map<Integer, Session> userSessions = new HashMap<>();
    private static final Gson gson = new Gson();
    MessageDAO messageDAO = new MessageDAO();

    @OnOpen
    public void onOpen(Session session, @PathParam("userID") int userID) {
        userSessions.put(userID, session);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userID") int userID, @PathParam("receiver") int receiver, @PathParam("productid") int productid) throws IOException {
        boolean flag = false;
        Message msg = new Message();
        AccountDAO accountDao = new AccountDAO();
        ProductDAO productDao = new ProductDAO();
        Date currentTime = new Date();

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        msg.setTimestamp(currentTimestamp);
        msg.setSenderId(userID);
        msg.setContent(message);
        if (message.contains("productid")) {
            String productids = message.split(":")[1];
            Product product = productDao.getProductsById(productids);
            String content = "Advise me about this product:"+ "ProductID: " + product.getId() + "ProductName:" + product.getProductName();
            msg.setContent(content);
        }
        
        Gson gson = new Gson();
        String jsonMessage = gson.toJson(msg);

        Session recipientSession = userSessions.get(receiver);
        Session userSendSession = userSessions.get(userID);

        if (recipientSession != null) {
            List<Account> userMsg = new ArrayList<Account>();
            userMsg = messageDAO.getUserMessages(receiver);
            for (Account account : userMsg) {
                if (account.getId() == userID) {
                    flag = true;
                }
            }
            if (!flag) {
                Account ac = accountDao.getAccountById(userID);
                String accountMessage = gson.toJson(ac);
                recipientSession.getBasicRemote().sendText(accountMessage);
            }
        }
        
        if (message != null && receiver != 0 && userID != 0) {
            int conversationId = messageDAO.getOrCreateConversation(userID, receiver);
            if (conversationId != -1) {
                messageDAO.addMessage(conversationId, userID, message, currentTimestamp);
            }
        }
        if (recipientSession != null && userSendSession != null) {
            recipientSession.getBasicRemote().sendText(jsonMessage);
            userSendSession.getBasicRemote().sendText(jsonMessage);
        } else {
            userSendSession.getBasicRemote().sendText(jsonMessage);
        }
    }

    @OnClose
    public void onClose(Session session) {
        for (Map.Entry<Integer, Session> entry : userSessions.entrySet()) {
            if (entry.getValue().equals(session)) {
                userSessions.remove(entry.getKey());
                break;
            }
        }
    }
}
