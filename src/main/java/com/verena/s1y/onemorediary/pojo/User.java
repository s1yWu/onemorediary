package com.verena.s1y.onemorediary.pojo;

import com.verena.s1y.onemorediary.annotation.Column;
import com.verena.s1y.onemorediary.annotation.Pk;
import com.verena.s1y.onemorediary.annotation.Table;
import com.verena.s1y.onemorediary.validator.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Table(name = "user")

@ApiModel(discriminator = "登陆用户实体类")
public class User {

    @Pk
    @ApiModelProperty("用户ID")
    private int id ;

    @Pattern(regexp="^1(3|4|5|7|8)\\d{9}$",message="手机号码格式错误！")
    @ApiModelProperty("用户手机号")
    private String phone ;

    @NotEmpty(message = "用户密码不可为空")
    @ApiModelProperty("用户密码")
    private String password ;

    @Column(name = "is_bind_wechat")
    @ApiModelProperty("用户是否绑定微信")
    private boolean isBindWeChat;

    @Column(name = "id_wechat")
    @ApiModelProperty("用户微信ID")
    private int idWeChat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBindWeChat() {
        return isBindWeChat;
    }

    public void setBindWeChat(boolean bindWeChat) {
        isBindWeChat = bindWeChat;
    }

    public int getIdWeChat() {
        return idWeChat;
    }

    public void setIdWeChat(int idWeChat) {
        this.idWeChat = idWeChat;
    }
}
