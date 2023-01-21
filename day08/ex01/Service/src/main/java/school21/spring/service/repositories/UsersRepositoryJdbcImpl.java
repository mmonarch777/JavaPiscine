package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import school21.spring.service.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    HikariDataSource ds;

    public UsersRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.ds = dataSource;
    }
    @Override
    public User findById(Long id) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from users where id=?;");
            ps.setLong(1, id);
            User user = null;
            ResultSet rSet = ps.executeQuery();
            if (rSet.next()) {
                Long userId = rSet.getLong("id");
                String userEmail = rSet.getString("email");
                user = new User(userId, userEmail);
            }
            rSet.close();
            return user;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from users;");
            ResultSet rSet = ps.executeQuery();
            List<User> list = new LinkedList<>();
            while (rSet.next()) {
                list.add(new User(rSet.getLong("id"), rSet.getString("email")));
            }
            return list;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(User entity) {
        try(Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("insert into users (email) values(?);");
            ps.setString(1, entity.getEmail());
            ps.executeUpdate();
            ResultSet rSet = ps.getGeneratedKeys();
            if (rSet.next()) {
                entity.setId(rSet.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("update users set email=? where id=?;");
            ps.setString(1, entity.getEmail());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("delete from users where id=?;");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from users where email=?;");
            ps.setString(1, email);
            ResultSet rSet = ps.executeQuery();
            User user = null;
            if (rSet.next()) {
                Long userId = rSet.getLong("id");
                String userEmail = rSet.getString("email");
                user = new User(userId, userEmail);
            }
            rSet.close();
            if (user != null) {
                return Optional.of(user);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return  Optional.empty();
    }
}
