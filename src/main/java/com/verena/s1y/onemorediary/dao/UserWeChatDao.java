package com.verena.s1y.onemorediary.dao;

import com.verena.s1y.onemorediary.dao.base.BaseDao;
import com.verena.s1y.onemorediary.pojo.UserWeChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserWeChatDao extends BaseDao<UserWeChat,Object> {


    @Autowired
    public UserWeChatDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public UserWeChat updateWeChatUserIfNotExist(UserWeChat userWeChat){
        List<UserWeChat> resultList = super.findByExample(userWeChat);
        if (resultList.size() == 0){
            super.insert()
        }
        return userWeChat;
    }

}
