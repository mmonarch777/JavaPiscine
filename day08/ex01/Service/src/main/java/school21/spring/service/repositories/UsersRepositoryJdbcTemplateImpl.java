package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private final JdbcTemplate jdbc;

    private final String SQL_FIND_BY_ID = "select * from users where id=?;";
    private final String SQL_SAVE = "insert into users (email) values(?);";
    private final String SQL_UPDATE = "update users set email = ? where id=?;";
    private final String SQL_DELETE = "delete from users where id=?;";
    private final String SQL_FIND_ALL = "select * from users;";
    private final String SQL_FIND_BY_EMAIL = "select * from users where id=?;";

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
    @Override
    public User findById(Long id) {
        return jdbc.query(SQL_FIND_BY_ID, new UserMapper(), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbc.query(SQL_FIND_ALL, new UserMapper());
    }

    @Override
    public void save(User entity) {
        jdbc.update(SQL_SAVE, entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbc.update(SQL_UPDATE, entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbc.update(SQL_DELETE, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = jdbc.query(SQL_FIND_BY_EMAIL, new UserMapper(), new Object[]{email})
                .stream().findAny().orElse(null);
        return Optional.of(user);
    }

    public class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }
}
