package com.verena.s1y.onemorediary.dao;

import com.verena.s1y.onemorediary.dao.base.BaseDao;
import com.verena.s1y.onemorediary.pojo.UserWeChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserWeChatDao extends BaseDao<UserWeChat,Object> {


    @Autowired
    public UserWeChatDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public UserWeChat updateWeChatUserIfNotExist(UserWeChat userWeChat){
        List<UserWeChat> resultList = super.findOneByKeyValue(userWeChat.getUid(),"uid");
       if (resultList.size() == 0){
           super.insert(userWeChat,true);
           resultList .addAll( super.findOneByKeyValue(userWeChat.getUid(),"uid"));
        }
        return resultList.get(0);
    }

    public Boolean updateWeChatUseId(String weChatId,String id){
      return   super.updateByKeyValue("id_user",id,weChatId,"id_wechat") > 0;
    }
}
