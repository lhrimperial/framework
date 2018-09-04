package com.github.framework.test.jdbc.dao.impl;

import com.github.framework.test.jdbc.dao.UserDao;
import com.github.framework.test.jdbc.domain.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    @Override
    public UserPO findByUserName(Integer id) {
        Connection connection = null;
        UserPO userPO = null;
        try {
            String sql = "select * from t_user where id = ?";
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userPO = new UserPO();
                userPO.setId(rs.getInt(0));
                userPO.setUserName(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userPO;
    }

    @Override
    public List<UserPO> findAllUser() {
        return jdbcTemplate.query("select * from t_user", new UserRowMapper());
    }

    @Override
    public void save(final UserPO userPO) {
        final String sql = "insert into t_user(id,user_name) values(?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, userPO.getId());
                ps.setString(2, userPO.getUserName());
                return ps;
            }
        });
    }

    @Override
    public void update(UserPO userPO) {
        jdbcTemplate.update(
                "update t_user set user_name=? where id=?",
                new Object[]{userPO.getUserName(),userPO.getId()});
    }

}

class UserRowMapper implements RowMapper<UserPO> {
    @Override
    public UserPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserPO user = new UserPO();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("user_name"));
        return user;
    }
}
