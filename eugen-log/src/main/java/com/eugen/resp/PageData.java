package com.eugen.resp;


import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PageData<T>
{
    private String code;

    private long totalSize;

    private int totalPage;

    private int currentPage;

    private int pageSize;

    List<T> data;
}
