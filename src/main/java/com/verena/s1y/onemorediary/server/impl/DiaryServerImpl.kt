package com.verena.s1y.onemorediary.server.impl

import com.verena.s1y.onemorediary.dao.DiaryDao
import com.verena.s1y.onemorediary.pojo.Diary
import com.verena.s1y.onemorediary.server.DiaryServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DiaryServerImpl : DiaryServer {


    var diaryDao : DiaryDao ?= null

    @Autowired
    fun DiaryServerImpl(diaryDao: DiaryDao){
        this.diaryDao = diaryDao
    }

    override fun insertDiary(diary: Diary): Boolean {
//        val st = StringBuffer()
//        if (!diary.image.isNotEmpty()){
//            for ( (i,d) in diary.image.withIndex()){
//                st.append(d)
//                if (i < diary.image.size -1)
//                    st.append(",")
//            }
//            diary.images = st.toString()
//        }
        return diaryDao?.insertDiary(diary)!!
    }

    override fun selectDiaryByIdUser(idUser: Int): List<Diary>  = diaryDao?.selectDiaryByIdUser(idUser)!!

}