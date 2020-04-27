package com.verena.s1y.onemorediary;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableSwagger2Doc
@SpringBootApplication()
public class OnemorediaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnemorediaryApplication.class, args);
    }

}
