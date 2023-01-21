package edu.school21.chat.models;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Objects;

public class Message {
    private final Long id;
    private final User author;
    private final Chatroom room;
    private final String text;
    private final Timestamp time_date;

    public Message(Long id, User author, Chatroom room, String text, Timestamp time_data) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.time_date = time_data;
    }

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
                "\nid=" + id +
                ",\nauthor=" + author +
                ",\nroom=" + room +
                ",\ntext='" + text + '\'' +
                ",\ntime_date=" + time_date +
                "}\n";
    }
}
