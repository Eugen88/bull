package com.eugen.log;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class EugenLogApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(EugenLogApplication.class, args);
    }
}
