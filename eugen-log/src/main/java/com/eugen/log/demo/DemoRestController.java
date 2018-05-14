package com.eugen.log.demo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eugen.log.log.Log;
import com.eugen.resp.ResponseList;


@RestController
@RequestMapping("/demo")
public class DemoRestController
{
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    @Log(name = "查询用户", source = 1, module = 1)
    public ResponseList getUser(@RequestParam("userName") String userName)
    {
        UserEntity entity = new UserEntity();
        entity.setUserName(userName);
        List<UserEntity> list = userService.get(entity);
        return new ResponseList(200, "", "", list);
    }
}
