package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{

   private final Connection connection;

   public ProductsRepositoryJdbcImpl() throws SQLException {
       DataSource ds = new EmbeddedDatabaseBuilder()
               .addScript("schema.sql")
               .addScript("data.sql")
               .build();
       connection = ds.getConnection();
   }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> list = new ArrayList<>();
        ResultSet set = connection
                .createStatement()
                .executeQuery("select * from Product");
        while (set.next()) {
            Product product = new Product(set.getLong("id"),
                    set.getString("name"),
                    set.getInt("price"));
            list.add(product);
        }
        return list;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        ResultSet set = connection
                .createStatement()
                .executeQuery("select * from Product where id=" +id);
        set.next();
        Product product = new Product(set.getLong("id"),
                set.getString("name"),
                set.getInt("price"));
        return Optional.of(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("update Product set name=?, price=? where id=?");
        ps.setString(1, product.getName());
        ps.setInt(2, product.getPrice());
        ps.setLong(3, product.getId());
        ps.executeUpdate();
    }

    @Override
    public void save(Product product) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("insert into Product (name, price) values (?, ?)");
        ps.setString(1, product.getName());
        ps.setInt(2, product.getPrice());
        ps.executeUpdate();
    }

    @Override
    public void delete(Long id) throws SQLException {
        connection.prepareStatement("delete from Product where id=" + id).executeUpdate();
    }
}
