package com.verena.s1y.onemorediary.dao;

import com.verena.s1y.onemorediary.dao.base.BaseDao;
import com.verena.s1y.onemorediary.pojo.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiaryDao extends BaseDao<Diary,Object> {

    @Autowired
    public DiaryDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public boolean insertDiary(Diary diary){
       return super.insert(diary,true) > 0;
    }

    public List<Diary> selectDiaryByIdUser(int idUser){
        return  super.findOneByKeyValue(idUser,"id_user");
    }

}
