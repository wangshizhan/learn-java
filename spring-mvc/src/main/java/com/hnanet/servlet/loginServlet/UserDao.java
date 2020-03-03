package com.hnanet.servlet.loginServlet;

import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
    public User getuser(User user){

        String sql = "select * from user where `name` = ? and `password` = ?";
        try {
            User loginUser = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),user.getName(),user.getPassword());
            return loginUser;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Test
    public void testJDBCUtils(){
        String sql = "select * from user";
        User user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class));
        System.out.println(user);
        User u = new User();
        u.setName("小马");
        u.setPassword("1234567");
        System.out.println(getuser(u));

    }

}

