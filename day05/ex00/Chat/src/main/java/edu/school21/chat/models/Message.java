package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private Date time_date;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Message m = (Message) o;
        return Objects.equals(id, m.id) && Objects.equals(author, m.author)
                && Objects.equals(room, m.room) && Objects.equals(text, m.text)
                && Objects.equals(time_date, m.time_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, time_date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", room=" + room +
                ", text='" + text + '\'' +
                ", time_date=" + time_date +
                '}';
    }
}
