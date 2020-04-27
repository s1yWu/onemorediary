package com.verena.s1y.onemorediary.server

interface TokenServer {

    fun checkToken(token : String ) : Boolean

    fun updateToken(token : String) : String

    fun createToken(id : Int,token: String = "") : String

}