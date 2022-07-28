package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private  long id;
    private  String login;
    private  String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> socializedRooms;

    public User(Long id, String login, String password, List<Chatroom> createdChatrooms, List<Chatroom> socializedChatrooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdChatrooms;
        this.socializedRooms = socializedChatrooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedChatrooms() {
        return createdRooms;
    }

    public void setCreatedChatrooms(List<Chatroom> createdChatrooms) {
        this.createdRooms = createdChatrooms;
    }

    public List<Chatroom> getSocializedChatrooms() {
        return socializedRooms;
    }

    public void setSocializedChatrooms(List<Chatroom> socializedChatrooms) {
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