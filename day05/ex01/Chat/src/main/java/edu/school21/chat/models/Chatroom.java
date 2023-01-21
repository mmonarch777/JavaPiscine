package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private final Long id;
    private final String name;
    private final User owner;
    private final List<Message> messageList;

    public Chatroom(Long id, String name, User owner, List<Message> messageList) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messageList = messageList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, messageList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        Chatroom cr = (Chatroom) obj;
        return Objects.equals(id, cr.id) && Objects.equals(name, cr.name)
                && Objects.equals(owner, cr.owner) && Objects.equals(messageList, ((Chatroom) obj).messageList);
    }


    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", messages=" + messageList +
                '}';
    }
}
