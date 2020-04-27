package com.verena.s1y.onemorediary;

import com.google.gson.Gson;
import com.verena.s1y.onemorediary.pojo.User;
import com.verena.s1y.onemorediary.pojo.base.Token;
import com.verena.s1y.onemorediary.server.UserServer;
import com.verena.s1y.onemorediary.util.AesEncodeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
class OnemorediaryApplicationTests {

    private final UserServer server;

    @Autowired
    OnemorediaryApplicationTests(UserServer server) {
        this.server = server;
    }


    @Test
    void contextLoads() throws UnsupportedEncodingException {
        long time = System.currentTimeMillis();
        Token token = new Token(1,"null",time+1000*60*60*3,time );
        String enToken = AesEncodeUtil.encrypt(new Gson().toJson(token));
        System.out.println(enToken.replaceAll("\r|\n*",""));
        String dcToken = AesEncodeUtil.decrypt(enToken);
        System.out.println(dcToken);
    }

    @Test
    void selectUserByKey() {
       User user =  server.selectUser("100","id");
       if (null == user)
           System.out.println("user is null");
       else
           System.out.println(user.toString());
    }

}
