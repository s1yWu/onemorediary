package com.verena.s1y.onemorediary.server

import com.verena.s1y.onemorediary.pojo.User

interface UserServer {

    fun selectUser(id : Int) : User

    fun insertUser(user : User) : Boolean

    fun updateUser(user : User) : Boolean

    fun deleteUser(id : Int) : Boolean

}