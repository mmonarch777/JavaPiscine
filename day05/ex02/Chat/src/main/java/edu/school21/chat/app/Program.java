package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        try (HikariDataSource dataSource = new HikariDataSource()){

            createTabls(dataSource);

            saveMessage(dataSource);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveMessage(HikariDataSource dataSource) {
        User author = new User(0L, "user", "777", new ArrayList<Chatroom>(), new ArrayList<Chatroom>());
        Chatroom room = new Chatroom(5L, "myRoom", author, new ArrayList<Message>());
        Message message = new Message(null, author, room, "Hello world", Timestamp.valueOf(LocalDateTime.now()));

        MessagesRepository mr = new MessagesRepositoryJdbcImpl(dataSource);
        mr.save(message);
        mr.save(message);
        System.out.println(message.getId());
        System.out.println(mr.findById(message.getId()).get());
    }
    public static void createTabls(HikariDataSource dataSource) {
        dataSource.setJdbcUrl("jdbc:postgresql://localhost/");
        dataSource.setUsername("mmonarch");
        dataSource.setPassword(null);

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            if (connection == null) {
                System.err.println("Error: connection DB");
                System.exit(-1);
            }
        } catch (SQLException e) {
            System.err.println("Error: connection DB");
            System.exit(-1);
        }
        List<String> schema = null;
        List<String> data = null;
        try {
            schema = Files.readAllLines(Paths.get("src/main/resources/schema.sql"));
            data = Files.readAllLines(Paths.get("src/main/resources/data.sql"));
        } catch (IOException e) {
            System.err.println("Error: file not found");
            System.exit(-1);
        }
        execSqlFile(connection, schema);
        execSqlFile(connection, data);

    }

    public static void getMessageById(HikariDataSource dataSource) {
        MessagesRepository mr = new MessagesRepositoryJdbcImpl(dataSource);
        System.out.println("Enter a message ID");
        long id = -1;
        try(Scanner scanner = new Scanner(System.in)) {
            id = scanner.nextLong();
        } catch (InputMismatchException e) {
            System.err.println("Error: input not long number");
            System.exit(-1);
        }

        Optional<Message> message = mr.findById(id);
        if (message.isPresent()) {
            System.out.println(message.get());
        } else {
            System.out.println("null");
        }
    }
    public static void execSqlFile(Connection connection, List<String> file) {
        try {
            StringBuilder builder = new StringBuilder();
            for (String line : file) {
                builder.append(line);
                if (line.endsWith(";")) {
                    connection.createStatement().execute(builder.toString());
                    builder.setLength(0);
                }
            }
        } catch (SQLException e) {
            System.err.println("Wrong command inside schema.sql");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
