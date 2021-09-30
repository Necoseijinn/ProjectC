package ProjectC.Gold.Note.JWeb.J2EEV1.dao;

import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category;
import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Product;
import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DAOUtils implements BaseDAO<Product> {

    Connection connection;

    @Override
    public void add(Product product) {

        connection = getConnection();
        /** 生成预编译sql语句 **/
        String sql = "INSERT INTO product_ values(null,?,?,?)";
        try {
            /***
             * 如果想要在插入数据的同时获取对应的主键,那就需要在prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)方法后再加一个参数,
             * 告诉PrepareStatement对象需要返回插入数据的主键。
             */
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            /***
             * 通过传入的Product实例字段的值来设定预编译sql语句的内容
             * 注意预编译sql语句匹配第一个"?"的角标是从1开始的。
             */
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setInt(3, product.getCategory().getId());
            /***
             * executeUpdate专用于执行插入更新删除语句
             * excuteSQL专用于执行查询语句
             * excute可以用于执行任何sql语句只不过比较复杂效率不是很高
             */
            ps.execute();

            /***
             * getGeneratedKeys()方法返回INSERT操作后生成的主键
             * 由于可以同时插入多条数据所以返回的主键也可能是多条
             * 由于主键并不一定是int类型，所以获取的时候必须指定主键的类型。
             * 由于不保证能插入成功，所以要先判断rs.next()。
             */
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                product.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void delete(Product product) {

        connection = getConnection();
        /** 生成预编译sql语句 **/
        String sql = "DELETE FROM product_ WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            /***
             * 通过传入的Product实例字段的值来设定预编译sql语句的内容
             * 注意预编译sql语句匹配第一个"?"的角标是从1开始的。
             */
            ps.setInt(1, product.getId());
            /** 执行预编译sql语句 **/
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void update(Product product) {

        connection = getConnection();
        String sql = "UPDATE product_ SET name = ?,price = ?,cid = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setInt(3, product.getCategory().getId());
            ps.setInt(4, product.getId());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public Product get(Product product) {

        connection = getConnection();
        Category category = new Category();
        String sql = "SELECT p.name,p.price,c.id,c.name FROM product_ p,category_ c WHERE c.id = p.cid AND p.id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, product.getId());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                product.setName(rs.getString(1));
                product.setPrice(rs.getFloat(2));
                category.setId(rs.getInt(3));
                category.setName(rs.getString(4));
                product.setCategory(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return product;

    }

    @Override
    public List<Product> list() {

        connection = getConnection();
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT p.id,p.name,p.price,c.id,c.name FROM product_ p,category_ c WHERE c.id = p.cid ORDER BY p.id ASC";
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                Product product = new Product();
                Category category = new Category();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getFloat(3));
                category.setId(rs.getInt(4));
                category.setName(rs.getString(5));
                product.setCategory(category);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return products.size() > 0 ? products : null;

    }

    @Override
    public List<Product> list(int start, int count) {

        connection = getConnection();
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT p.id,p.name,p.price,c.id,c.name FROM product_ p,category_ c WHERE c.id = p.cid ORDER BY p.id ASC LIMIT ? , ?";
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                Product product = new Product();
                Category category = new Category();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getFloat(3));
                category.setId(rs.getInt(4));
                category.setName(rs.getString(5));
                product.setCategory(category);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return products.size() > 0 ? products : null;

    }

    @Override
    public int getTotal() {

        connection = getConnection();
        String sql = "SELECT COUNT(*) FROM product_";
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return count;

    }
}
