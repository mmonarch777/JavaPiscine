DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS chatrooms;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    login       VARCHAR NOT NULL,
    password    VARCHAR NOT NULL
);

CREATE TABLE chatrooms
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    owner    BIGINT  NOT NULL
);

CREATE TABLE messages
(
    id        BIGSERIAL PRIMARY KEY,
    author    BIGINT    NOT NULL,
    room      BIGINT    NOT NULL,
    text      TEXT,
    date_time TIMESTAMP
);

ALTER TABLE chatrooms
    ADD CONSTRAINT fk_owner FOREIGN KEY (owner) REFERENCES users (id);

ALTER TABLE messages
    ADD CONSTRAINT fk_author FOREIGN KEY (author) REFERENCES users (id);

ALTER TABLE messages
    ADD CONSTRAINT fk_room FOREIGN KEY (room) REFERENCES chatrooms (id);
