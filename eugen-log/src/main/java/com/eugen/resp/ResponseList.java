package com.eugen.resp;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 列表数据响应
 * 
 * @author Eugen
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseList extends Response
{

    /**
     * 数据
     */
    private List<?> list;

    public ResponseList(int status, String code, String desc, List<?> list)
    {
        super(status, code, desc);
        this.list = list;
    }
}
