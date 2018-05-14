package com.eugen.log.log.operate;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operate_log_t")
public class OperateLogEntity implements Serializable
{
    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -1356171309829576844L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "operate_name", nullable = false, length = 64)
    private String operateName;

    @Column(name = "operate_desc", length = 20000)
    private String operateDesc;

    @Column(name = "module", nullable = false)
    private int module;

    @Column(name = "source", nullable = false)
    private int source;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "operate_result", nullable = false)
    private String operateResult;
}
