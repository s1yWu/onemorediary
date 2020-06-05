package com.verena.s1y.onemorediary.server

import com.verena.s1y.onemorediary.pojo.Diary

interface DiaryServer {

    fun insertDiary(diary: Diary) : Boolean

    fun selectDiaryByIdUser(idUser : Int) : List<Diary>

}