package com.verena.s1y.onemorediary.server.impl

import com.verena.s1y.onemorediary.dao.UserWeChatDao
import com.verena.s1y.onemorediary.pojo.UserWeChat
import com.verena.s1y.onemorediary.server.UserWeChatServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserWeChatServerImpl :UserWeChatServer {


    var userWeChatDao : UserWeChatDao ?= null

   @Autowired
    fun UserWeChatServerImpl(userWeChatDao: UserWeChatDao){
        this.userWeChatDao = userWeChatDao
    }

    override fun updateWeChatUserIfNotExist(userWeChat: UserWeChat) = userWeChatDao?.updateWeChatUserIfNotExist(userWeChat)
}