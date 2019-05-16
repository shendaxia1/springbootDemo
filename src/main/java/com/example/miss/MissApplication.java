package com.example.miss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.miss.mapper")
@SpringBootApplication
public class MissApplication {

    public static void main(String[] args) {
        SpringApplication.run(MissApplication.class, args);
    }

}
