package com.verena.s1y.onemorediary.pojo;

import com.verena.s1y.onemorediary.annotation.Column;
import com.verena.s1y.onemorediary.annotation.Pk;
import com.verena.s1y.onemorediary.annotation.Table;
import lombok.Data;


@Data
@Table(name = "user_from_wechat")
public class UserWeChat {

    @Pk
    @Column(name = "id_wechat")
    private int id_wechat;

    @Column(name = "id_user")
    private int id_user;

    private String avatarUrl;

    private String city;

    private String country;

    private String gender;

    private String language;

    private String nickName;

    private String province;

    private String code;

    private String uid;

    private String secret;

}
