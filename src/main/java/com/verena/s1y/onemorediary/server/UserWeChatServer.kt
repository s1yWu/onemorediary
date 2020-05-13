package com.verena.s1y.onemorediary.server

import com.verena.s1y.onemorediary.pojo.UserWeChat

interface UserWeChatServer {

    fun updateWeChatUserIfNotExist(userWeChat : UserWeChat): UserWeChat?

}