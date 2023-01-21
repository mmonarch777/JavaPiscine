package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl extends JdbcTemplate implements UsersRepository{

    public UsersRepositoryJdbcImpl(HikariDataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public List<User> findAll(int page, int size) {
        return super.query(
                String.format("SELECT * FROM users LIMIT %d OFFSET %d", size, (page - 1) * size),
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("login"),
                        rs.getString("password"), new ArrayList<>(), new ArrayList<>()));
    }

}
