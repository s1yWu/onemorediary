package com.verena.s1y.onemorediary.server.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.verena.s1y.onemorediary.pojo.base.Token
import com.verena.s1y.onemorediary.server.TokenServer
import com.verena.s1y.onemorediary.util.AesEncodeUtil


import org.springframework.stereotype.Service
@Service("tokenServer")
class TokenServerImpl : TokenServer {
    override fun checkToken(token: String)
            = with(token) {

        val tokenBean = Gson().fromJson<Token>(AesEncodeUtil.decrypt(token),object : TypeToken<Token>(){}.type)
        System.currentTimeMillis() - tokenBean.expireTime < 0
    }

    override fun updateToken(token: String) = with(token){
        var tokenBean = Gson().fromJson<Token>(AesEncodeUtil.decrypt(token),object : TypeToken<Token>(){}.type)
        createToken(tokenBean.userId,tokenBean.token)
    }!!

    override fun createToken(id: Int, token: String): String {
        val time = System.currentTimeMillis()
        val tokenBean = Token(id,token,time + 1000*60*60*72,time )
        return AesEncodeUtil.encrypt(Gson().toJson(tokenBean))
    }

}