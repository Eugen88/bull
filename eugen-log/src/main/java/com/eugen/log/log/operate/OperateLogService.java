package com.eugen.log.log.operate;


import java.util.List;


public interface OperateLogService
{
    List<OperateLogEntity> get();

    boolean add(OperateLogEntity entity);
}
