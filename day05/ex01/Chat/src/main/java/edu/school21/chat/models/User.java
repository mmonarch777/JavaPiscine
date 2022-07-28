package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private final long id;
    private final String login;
    private final String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> socializedRooms;

    public User(Long id, String login, String password, List<Chatroom> createdChatrooms, List<Chatroom> socializedChatrooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdChatrooms;
        this.socializedRooms = socializedChatrooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(createdRooms, user.createdRooms) && Objects.equals(socializedRooms, user.socializedRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, socializedRooms);
    }

    @Override
    public String
    toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", socializedRooms=" + socializedRooms +
                '}';
    }
}