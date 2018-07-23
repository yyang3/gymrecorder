package com.fitrecorder.gymrecorder.mysql;

import com.fitrecorder.gymrecorder.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(User user) {
        jdbcTemplate.update("insert into FIT_user (userName, surName, givenName, pwd) values (?,?,?,?)",
                            user.getUserName(), user.getSurName(),
                            user.getGivenName(), user.getPwd());
    }

    @Override
    public void delete(String userName) {
        jdbcTemplate.update("delete from fit_user where userName = ?", userName);
    }


    @Override
    public Integer countUsers() {
        return jdbcTemplate.queryForObject("select count(*) from fit_user", Integer.class);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query("select * from fit_user", getRowMapper());
        return users;
    }

    @Override public User getUserByUserName(String userName) {
        return jdbcTemplate.query("select * from fit_user where userName = ?", getRowMapper(), userName).get(0);
    }

    @Override public void updateUser(User user) {
        jdbcTemplate.update("UPDATE fit_user (userName, surname, givenName, pwd) values (?,?,?,?)", user.getUserName(),
                            user.getGivenName(), user.getSurName(), user.getPwd());
    }

    private RowMapper<User> getRowMapper() {
        return ((rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("userName"));
            user.setSurName(rs.getString("surname"));
            user.setGivenName(rs.getString("givenName"));
            user.setPwd(rs.getString("pwd"));
            return user;
        });
    }
}
