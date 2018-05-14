package com.eugen.resp;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 分页数据响应
 * 
 * @author Eugen
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePage extends Response
{

    /**
     * 分页数据节点
     */
    private Page page;

    public ResponsePage(int status, String code, String desc, Page page)
    {
        super(status, code, desc);
        this.page = page;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Page
    {
        /**
         * 总页数
         */
        private long totalPage;

        /**
         * 总记录数
         */
        private long totalSize;

        /**
         * 当前页数
         */
        private int currentPage;

        /**
         * 每页记录数
         */
        private int pageSize;

        /**
         * 数据
         */
        private List<?> list;
    }
}
