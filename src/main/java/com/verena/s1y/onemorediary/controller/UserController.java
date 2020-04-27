package com.verena.s1y.onemorediary.controller;

import com.verena.s1y.onemorediary.constant.Status;
import com.verena.s1y.onemorediary.exception.BaseException;
import com.verena.s1y.onemorediary.pojo.User;
import com.verena.s1y.onemorediary.pojo.base.ApiResponse;
import com.verena.s1y.onemorediary.server.UserServer;
import com.verena.s1y.onemorediary.validator.IsMobile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/one-more-dairy/user")
public class UserController {


    private final UserServer userServer;

    @Autowired
    public UserController(UserServer userServer) {
        this.userServer = userServer;
    }

    @GetMapping("/login/")
    @ApiOperation(value = "用户登陆")
    public String Login() throws BaseException {
        throw new RuntimeException();
    }


    @PostMapping("/")
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    public ApiResponse createUser(
            @Valid User user){
        if (userServer.insertUser(user))
            return ApiResponse.ofSuccess(user);
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
