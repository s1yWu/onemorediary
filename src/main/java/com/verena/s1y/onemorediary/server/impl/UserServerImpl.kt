package com.verena.s1y.onemorediary.server.impl

import com.verena.s1y.onemorediary.dao.UserDao
import com.verena.s1y.onemorediary.pojo.User
import com.verena.s1y.onemorediary.server.UserServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServerImpl : UserServer {

    var userDao : UserDao ?= null

    @Autowired
    fun UserServerImpl(userDao: UserDao){
        this.userDao = userDao
    }

    override fun selectUser(id: Int) = userDao?.findOneById(id.toLong())!!

    override fun insertUser(user: User) = with(user){
        userDao?.insert(user)!! > 0
    }

    override fun updateUser(user: User) = userDao?.update(user,user.id.toLong())!! >0

    override fun deleteUser(id: Int) = userDao?.delete(id.toLong())!! >0

}