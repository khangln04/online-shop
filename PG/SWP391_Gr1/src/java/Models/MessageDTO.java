/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Timestamp;

/**
 *
 * @author DoanManhTai
 */
public class MessageDTO {
    private int messageId;
    private int conversationId;
    private int senderId;
    private String content;
    private Timestamp timestamp;
    private int participant1Id;
    private int participant2Id;

    public MessageDTO() {
    }

    public MessageDTO(int messageId, int conversationId, int senderId, String content, Timestamp timestamp, int participant1Id, int participant2Id) {
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
        this.participant1Id = participant1Id;
        this.participant2Id = participant2Id;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getParticipant1Id() {
        return participant1Id;
    }

    public void setParticipant1Id(int participant1Id) {
        this.participant1Id = participant1Id;
    }

    public int getParticipant2Id() {
        return participant2Id;
    }

    public void setParticipant2Id(int participant2Id) {
        this.participant2Id = participant2Id;
    }
    
    
}
