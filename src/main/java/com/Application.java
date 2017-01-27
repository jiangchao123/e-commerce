package com;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by jiangchao08 on 16/12/5.
 */
@SpringBootApplication
//开启注解事务管理
@EnableTransactionManagement
@MapperScan("com.mapper")
public class Application {
    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }
}
