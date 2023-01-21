package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource ds;

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Message> findById(long id) {
        try {
            Connection connection = ds.getConnection();

            ResultSet messageSet = connection.createStatement().executeQuery( "SELECT * FROM messages WHERE id=" + id);
            messageSet.next();
            Long messageId = messageSet.getLong("id");
            Long authorId = messageSet.getLong("author");
            Long roomId = messageSet.getLong("room");
            String message = messageSet.getString("text");
            Timestamp timestamp = messageSet.getTimestamp("time_date");

            ResultSet authorSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE id=" + authorId);
            authorSet.next();
            String authorLogin = authorSet.getString("login");
            String authorPass = authorSet.getString("password");

            ResultSet roomSet = connection.createStatement().executeQuery("SELECT * FROM chatrooms WHERE id=" + roomId);
            roomSet.next();
            String roomName = roomSet.getString("name_room");

            Message msg = new Message(messageId,
                    new User(authorId, authorLogin, authorPass, null, null),
                    new Chatroom(roomId, roomName, null, null),
                    message, timestamp);
            return Optional.of(msg);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return Optional.empty();
        }
    }
}
