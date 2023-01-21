package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

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

            initDataSource(dataSource);
            createTabls(dataSource);


            UsersRepository ur = new UsersRepositoryJdbcImpl(dataSource);
            List<User> list = ur.findAll(2, 3);
            for (User user : list) {
                System.out.println(user);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateMessage(HikariDataSource dataSource) {
        MessagesRepository mr = new MessagesRepositoryJdbcImpl(dataSource);
        Optional<Message> mes = mr.findById(2);

        if (mes.isPresent()) {
            Message message = mes.get();
            System.out.println("Message before: \n" + message);
            message.setText("new message");
            message.setTime_date(Timestamp.valueOf(LocalDateTime.now()));
//            message.setTime_date(null);
            message.setAuthor(new User(1L, "hel", "hel", null, null));
            mr.update(message);
            Optional<Message> m = mr.findById(message.getId());
            System.out.println("\nMessage after: \n" +m.get());
        }
    }

    public static void saveMessage(HikariDataSource dataSource) {
        User author = new User(1L, "user", "777", new ArrayList<Chatroom>(), new ArrayList<Chatroom>());
        Chatroom room = new Chatroom(5L, "myRoom", author, new ArrayList<Message>());
        Message message = new Message(null, author, room, "Hello world", Timestamp.valueOf(LocalDateTime.now()));

        MessagesRepository mr = new MessagesRepositoryJdbcImpl(dataSource);
        mr.save(message);
        System.out.println(message.getId());
        System.out.println(mr.findById(message.getId()).get());
    }
    public static void initDataSource(HikariDataSource dataSource) {
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/");
        dataSource.setUsername(null);
        dataSource.setPassword(null);
    }
    public static void createTabls(HikariDataSource dataSource) {
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
