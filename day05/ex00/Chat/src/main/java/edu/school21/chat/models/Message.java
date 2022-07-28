package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {
    private int id;
    private User author;
    private Chatroom chatroom;
    private String text;
    private Date date;

    Message (String msgText, Date date) {
        this.text = msgText;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && author == message.author && chatroom == message.chatroom && Objects.equals(text, message.text) && Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, chatroom, text, date);
    }

    @Override
    public String
    toString() {
        return "Message {" +
                "id=" + id +
                ", authorID=" + author +
                ", chatroomID=" + chatroom +
                ", msgText='" + text + '\'' +
                ", msgDate=" + date +
                '}';
    }
}