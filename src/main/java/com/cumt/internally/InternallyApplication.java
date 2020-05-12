package com.cumt.internally;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cumt.internally.mapper")
@SpringBootApplication
public class InternallyApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternallyApplication.class, args);
    }

}
