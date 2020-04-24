package com.verena.s1y.onemorediary.pojo

import lombok.Data

@Data
data class User(
                private val id : Int ,
                private val phone : String,
                private val password :String,
                private val is_bind_wechat : Boolean,
                private val id_wechat : Int)
