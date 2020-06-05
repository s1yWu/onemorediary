package com.verena.s1y.onemorediary.pojo;

import com.verena.s1y.onemorediary.annotation.Column;
import com.verena.s1y.onemorediary.annotation.Pk;
import com.verena.s1y.onemorediary.annotation.Table;
import lombok.Data;

@Data
@Table(name = "diary")
public class Diary {

    @Pk
    private int id_diary;

    private int id_user;

    private String time_full;

    private String time_simple;

    private String wea;

    private String wea_img;

    private String html;

    private String week;

    @Column(name = "images")
    private String images;

    private String now_city;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

}
