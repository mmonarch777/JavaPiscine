package edu.school21.chat.repositories;

import edu.school21.chat.app.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource ds;
    private Connection connection;

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
        initConnection(ds);
    }

    @Override
    public Optional<Message> findById(long id) {
        try {
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

    @Override
    public void save(Message message) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into messages (author, room, text, time_date) values (?, ?, ?, ?)");
            ps.setLong(1, message.getAuthor().getId());
            ps.setLong(2, message.getRoom().getId());
            ps.setString(3, message.getText());
            ps.setTimestamp(4, message.getTime_date());
            ps.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            throw new NotSavedSubEntityException("Wrong prepare statement");
        }

        ResultSet rs = null;
        try {
            rs = connection.createStatement().executeQuery("select * from messages");
            while(rs.next()) {
                if (rs.isLast()) {
                    message.setId(rs.getLong("id"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
        }
    }

    @Override
    public void update(Message message) {
        try {
            PreparedStatement ps = connection.prepareStatement("update messages set " +
                    "author=?, room=?, text=?, time_date=? where id=?;");
            ps.setLong(1, message.getAuthor().getId());
            ps.setLong(2, message.getRoom().getId());
            ps.setString(3, message.getText());
            ps.setTimestamp(4, message.getTime_date());
            ps.setLong(5, message.getId());
            ps.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            throw new NotSavedSubEntityException("Error update\n" + e.getMessage());
        }
    }


    private void initConnection(DataSource ds) {
        try {
            this.connection = ds.getConnection();
        } catch (SQLException e) {
            System.err.println("Error : connection");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
