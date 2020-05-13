package com.verena.s1y.onemorediary.server

import com.verena.s1y.onemorediary.pojo.User
import com.verena.s1y.onemorediary.pojo.UserWeChat

interface UserServer {

    fun selectUser(value : Any,key : String) : User?

    fun insertUser(user : User) : Boolean

    fun updateUser(user : User) : Boolean

    fun deleteUser(id : Int) : Boolean



}