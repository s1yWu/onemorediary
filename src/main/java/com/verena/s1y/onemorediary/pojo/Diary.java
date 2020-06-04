package com.verena.s1y.onemorediary.pojo;

import lombok.Data;

@Data
public class Diary {

    private int id_user;

    private String time_full;

    private String time_simple;

    private String wea;

    private String wea_img;

    private String html;

    private String[] images;

    private String now_city;

}
