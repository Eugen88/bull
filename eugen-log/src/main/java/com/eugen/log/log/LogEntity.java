package com.eugen.log.log;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class LogEntity
{
    private String packageName;

    private String name;

    private int source;

    private int module;
}
