package com.eugen.log.log.operate.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eugen.log.log.operate.OperateLogEntity;
import com.eugen.log.log.operate.OperateLogRepository;
import com.eugen.log.log.operate.OperateLogService;


@Service("operateLogService")
public class OperateLogServiceImpl implements OperateLogService
{
    @Autowired
    private OperateLogRepository operateLogRepository;

    @Override
    public List<OperateLogEntity> get()
    {
        return operateLogRepository.findAll();
    }

    @Override
    public boolean add(OperateLogEntity entity)
    {
        return null != operateLogRepository.save(entity);
    }

}
