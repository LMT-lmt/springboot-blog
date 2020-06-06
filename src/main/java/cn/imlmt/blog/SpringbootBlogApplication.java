package cn.imlmt.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//为mapper包下的类都添加@mapper注解
@MapperScan(value = "cn.imlmt.blog.mapper")
@SpringBootApplication
public class SpringbootBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogApplication.class, args);
    }

}
