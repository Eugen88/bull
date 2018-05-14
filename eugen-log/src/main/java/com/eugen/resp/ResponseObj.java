package com.eugen.resp;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 返回对象数据
 * 
 * @author Eugen
 * @version 2018年5月8日
 * @see ResponseObj
 * @since
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObj extends Response
{
    private Object data;

    public ResponseObj(int status, String code, String desc, Object data)
    {
        super(status, code, desc);
        this.data = data;
    }
}
