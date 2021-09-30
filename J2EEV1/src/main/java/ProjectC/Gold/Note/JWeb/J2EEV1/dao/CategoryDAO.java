package ProjectC.Gold.Note.JWeb.J2EEV1.dao;

import ProjectC.Gold.Note.JWeb.J2EEV1.pojo.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DAOUtils implements BaseDAO<Category> {

    Connection connection;

    @Override
    public void add(Category category) {

        connection = getConnection();
        String sql = "INSERT INTO category_ VALUES(null,?)";
        try {
            /***
             * 如果想要在插入数据的同时获取对应的主键,那就需要在prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)方法后再加一个参数,
             * 告诉PrepareStatement对象需要返回插入数据的主键。
             */
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            /***
             * executeUpdate专用于执行插入更新删除语句
             * excuteSQL专用于执行查询语句
             * excute可以用于执行任何sql语句只不过比较复杂效率不是很高
             */
            ps.executeUpdate();
            /***
             * getGeneratedKeys()方法返回INSERT操作后生成的主键
             * 由于可以同时插入多条数据所以返回的主键也可能是多条
             * 由于主键并不一定是int类型，所以获取的时候必须指定主键的类型。
             * 由于不保证能插入成功，所以要先判断rs.next()。
             */
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                category.setId(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void delete(Category category) {

        connection = getConnection();
        String sql = "DELETE FROM category_ WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, category.getId());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void update(Category category) {

        connection = getConnection();
        String sql = "UPDATE category_ SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public Category get(Category category) {

        connection = getConnection();
        String sql = "SELECT * FROM category_ WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, category.getId());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                category.setName(rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return category;

    }

    @Override
    public List<Category> list() {

        connection = getConnection();
        List<Category> categories = new ArrayList<Category>();
        String sql = "SELECT * FROM category_ ORDER BY id ASC";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return categories.size() > 0 ? categories : null;

    }

    @Override
    public List<Category> list(int start, int count) {

        connection = getConnection();
        List<Category> categories = new ArrayList<Category>();
        String sql = "SELECT * FROM category_ ORDER BY id ASC LIMIT ? , ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return categories.size() > 0 ? categories : null;

    }

    @Override
    public int getTotal() {

        connection = getConnection();
        String sql = "SELECT COUNT(*) FROM category_";
        int count = 0;
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
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
