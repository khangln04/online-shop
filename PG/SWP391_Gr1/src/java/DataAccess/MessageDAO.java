/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Account;
import Models.MessageDTO;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DoanManhTai
 */
public class MessageDAO extends MyDAO {

    public ArrayList<MessageDTO> getMessagesBySenderId(int sender_id, int receiver) {
        ArrayList<MessageDTO> listMessageDTO = new ArrayList<>();

        xSql = "SELECT m.message_id\n"
                + "		, m.conversation_id\n"
                + "		, m.sender_id\n"
                + "		, m.content\n"
                + "		, m.timestamp\n"
                + "		, c.participant1_id\n"
                + "		, c.participant2_id\n"
                + "FROM Messages as m\n"
                + "FULL JOIN Conversations as c ON m.conversation_id = c.conversation_id\n"
                + "WHERE (c.participant1_id = ? and c.participant2_id = ?) OR (c.participant1_id = ? and c.participant2_id = ?)\n"
                + "ORDER BY c.conversation_id , m.timestamp";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, sender_id);
            ps.setInt(2, receiver);
            ps.setInt(3, receiver);
            ps.setInt(4, sender_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                MessageDTO message = new MessageDTO();
                message.setMessageId(rs.getInt("message_id"));
                message.setConversationId(rs.getInt("conversation_id"));
                message.setSenderId(rs.getInt("sender_id"));
                message.setContent(rs.getString("content"));
                message.setTimestamp(rs.getTimestamp("timestamp"));
                message.setParticipant1Id(rs.getInt("participant1_id"));
                message.setParticipant2Id(rs.getInt("participant2_id"));
                listMessageDTO.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMessageDTO;
    }

    public int getOrCreateConversation(int senderId, int recipientId) {
        int conversationId = -1;
        xSql = "SELECT conversation_id FROM Conversations WHERE (participant1_id = ? AND participant2_id = ?) OR (participant1_id = ? AND participant2_id = ?)";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, senderId);
            ps.setInt(2, recipientId);
            ps.setInt(3, recipientId);
            ps.setInt(4, senderId);

            rs = ps.executeQuery();

            if (rs.next()) {
                conversationId = rs.getInt("conversation_id");
            } else {
                xSql = "INSERT INTO Conversations (participant1_id, participant2_id) VALUES (?, ?)";
                ps = con.prepareStatement(xSql);
                ps.setInt(1, senderId);
                ps.setInt(2, recipientId);
                int result = ps.executeUpdate();
                if (result > 0) {
                    xSql = "SELECT TOP (1) [conversation_id]\n"
                            + "      ,[participant1_id]\n"
                            + "      ,[participant2_id]\n"
                            + "      ,[start_time]\n"
                            + "  FROM [SWP391_Gr1].[dbo].[Conversations]\n"
                            + "  ORDER BY [conversation_id] DESC";
                    ps = con.prepareStatement(xSql);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        conversationId = rs.getInt("conversation_id");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conversationId;
    }

    public void addMessage(int conversationId, int senderId, String content, Timestamp time) {
        xSql = "INSERT INTO Messages (conversation_id, sender_id, content,timestamp) VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);

            ps.setInt(1, conversationId);
            ps.setInt(2, senderId);
            ps.setString(3, content);
            ps.setTimestamp(4, time);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Account> getUserMessages(int participantId) {
        List<Account> userMessages = new ArrayList<>();
        try {
            xSql = "SELECT u.id, u.fullName, u.avatar, MAX(m.timestamp) AS latest_timestamp\n"
                    + "FROM Messages m\n"
                    + "INNER JOIN [User] u ON m.sender_id = u.id\n"
                    + "INNER JOIN Conversations c ON m.conversation_id = c.conversation_id\n"
                    + "WHERE (c.participant1_id = ? OR c.participant2_id = ?)\n"
                    + "  AND m.sender_id != ?\n"
                    + "GROUP BY u.id, u.fullName, u.avatar\n"
                    + "ORDER BY latest_timestamp DESC";

            ps = con.prepareStatement(xSql);
            ps.setInt(1, participantId);
            ps.setInt(2, participantId);
            ps.setInt(3, participantId);

            rs = ps.executeQuery();

            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setFullname(rs.getString("fullName"));
                a.setAvatar(rs.getString("avatar"));
                userMessages.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMessages;
    }
}
