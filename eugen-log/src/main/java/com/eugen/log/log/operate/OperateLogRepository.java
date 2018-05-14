package com.eugen.log.log.operate;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("operateLogRepository")
public interface OperateLogRepository extends JpaRepository<OperateLogEntity, Long>
{

}
