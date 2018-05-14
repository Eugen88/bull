package com.eugen.resp;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 定义返回数据格式
 * 
 * @author Eugen
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response
{
    /**
     * 返回状态码，定义200时成功，其它失败
     */
    private int status;

    /**
     * 自定义编码
     */
    private String code;

    /**
     * 返回描述
     */
    private String desc;

}
