package com.verena.s1y.onemorediary.pojo.base;

import lombok.Data;


@Data
public class Token {

    private static final long serialVersionUID = 5584132314624077161L;

    public Token(){}

    public Token(int userId, String token, long expireTime, long updateTime) {
        this.userId = userId;
        this.token = token;
        this.expireTime = expireTime;
        this.updateTime = updateTime;
    }

    /**
     *  用户ID
     */
    private int userId;
    /**
     * token
     */
    private String token;
    /**
     *  过期时间
     */
    private long expireTime;
    /**
     *  修改时间
     */
    private long updateTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
