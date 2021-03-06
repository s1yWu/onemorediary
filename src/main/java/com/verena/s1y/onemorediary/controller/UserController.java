package com.verena.s1y.onemorediary.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.verena.s1y.onemorediary.constant.Status;
import com.verena.s1y.onemorediary.exception.BaseException;
import com.verena.s1y.onemorediary.interceptor.Login;
import com.verena.s1y.onemorediary.pojo.User;
import com.verena.s1y.onemorediary.pojo.UserWeChat;
import com.verena.s1y.onemorediary.pojo.base.ApiResponse;
import com.verena.s1y.onemorediary.server.TokenServer;
import com.verena.s1y.onemorediary.server.UserServer;
import com.verena.s1y.onemorediary.server.UserWeChatServer;
import com.verena.s1y.onemorediary.validator.IsMobile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static com.verena.s1y.onemorediary.constant.Status.PHONE_ALREADY_BIND;

@Api(tags = "用户管理")
@RestController
@Slf4j
@RequestMapping(value = "/one-more-dairy/user")
public class UserController {


    private final UserServer userServer;
    private final UserWeChatServer userWeChatServer;
    private final TokenServer tokenServer;


    private final String APPID= "appid=wxc9b7a56c4ba1cc56&secret=a75f5ba3117caab23016b8e646d767eb";

    private final String APP_SECRET= "secret=a75f5ba3117caab23016b8e646d767eb";

    private final String GRANT_TYPE=  "grant_type=authorization_code";

    @Autowired
    public UserController(UserServer userServer, UserWeChatServer userWeChatServer, TokenServer tokenServer) {
        this.userServer = userServer;
        this.userWeChatServer = userWeChatServer;
        this.tokenServer = tokenServer;
    }

    @Login
    @GetMapping("/loginWx/")
    @ApiOperation(value = "微信用户登陆")
    public ApiResponse Login(UserWeChat userWeChat) throws BaseException {
        String wxResult =  getWeChatUserUid(userWeChat.getCode());
        Map<String, String> map = new Gson().fromJson(wxResult, new TypeToken<Map<String, String>>() { }.getType());
        if (StringUtils.isEmpty(map.get("openid")))
            return ApiResponse.ofFail(Status.WECHAT_GET_SECRET_ERROR,userWeChat);
        userWeChat.setSecret(map.get("session_key"));
        userWeChat.setUid(map.get("openid"));
        UserWeChat userWeChats = userWeChatServer.updateWeChatUserIfNotExist(userWeChat);

       return ApiResponse.ofSuccess(userWeChatServer.updateWeChatUserIfNotExist(userWeChat),tokenServer.createToken(userWeChat.getId_user(),"noting"));
    }

    private String getWeChatUserUid(String code) {
        try {
            HttpURLConnection connection = (HttpURLConnection)new URL("https://api.weixin.qq.com/sns/jscode2session?"
            +APPID +"&" +
                    APP_SECRET + "&" +
                    GRANT_TYPE + "&js_code=" +code).openConnection();
            int responseCode = 0;

            responseCode = connection.getResponseCode();
            InputStream inputStream;
            if (200 <= responseCode && responseCode <= 299) {
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }
            BufferedReader in = new BufferedReader( new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String currentLine;
            while ((currentLine = in.readLine()) != null) response.append(currentLine);
            in.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Login
    @PostMapping("/")
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    public ApiResponse createUser(
            @Valid User user){
        if (userServer.selectUser(user.getPhone(),"phone")!= null)
            return ApiResponse.ofFail(PHONE_ALREADY_BIND,user);

        if (userServer.insertUser(user)){
            user = userServer.selectUser(user.getIdWeChat(),"id_wechat");
            System.out.println(user.toString());
            userWeChatServer.updateWeChatUserId(user.getIdWeChat()+"",user.getId()+"");
            return ApiResponse.ofSuccess(user);
        }

        else
            return ApiResponse.ofFail(Status.SQL_INSERT_ERROR,user);
    }

    @PutMapping("/")
    @ApiOperation(value = "修改用户", notes = "根据User对象修改用户")
    public ApiResponse updateUser(
             @Valid
             User user){
        if (userServer.insertUser(user))
            return ApiResponse.ofSuccess(user);
        else
            return ApiResponse.ofFail(Status.SQL_INSERT_ERROR,user);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    public ApiResponse deleteUser(
            @Valid
            @IsMobile
            @PathVariable int id){
       return ApiResponse.ofSuccess(null);
    }
}
