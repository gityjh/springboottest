package com.example.springboot.demo.test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@PropertySource(value = {"classpath:student.properties"})
@ConfigurationProperties(prefix = "student")
public class Student {
    //@Value("${student.userName}")
    private String userName;
    private String sex;
    private Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
