package com.eugen.log.log.operate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eugen.resp.ResponseList;


@RestController
@RequestMapping("/operate")
public class OperateLogController
{
    @Autowired
    private OperateLogService operateLogService;

    @RequestMapping("/get")
    public ResponseList get()
    {
        ResponseList resp = new ResponseList();
        resp.setStatus(200);
        resp.setDesc("Success");
        resp.setList(operateLogService.get());
        return resp;
    }
}
