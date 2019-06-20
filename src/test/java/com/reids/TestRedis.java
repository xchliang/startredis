package com.reids;

import com.redis.entity.Student;
import com.redis.entity.Subject;
import com.redis.util.RedisUtil;

import java.util.ArrayList;
import java.util.List;

public class TestRedis {


    public static void main(String[] args) {
       // RedisUtil.set("name", "张三aa");
       // System.out.println(RedisUtil.get("name"));
        Student stu = new Student();
        stu.setName("小明");
        stu.setAge(12);
        stu.setSex("male");
        List<Subject> subjects = new ArrayList<Subject>();
        Subject sub = new Subject();
        sub.setName("语文");
        sub.setCode("001");
        subjects.add(sub);
        sub = new Subject();
        sub.setName("数学");
        sub.setCode("002");
        subjects.add(sub);
        stu.setSubjects(subjects);
        String key = "小明";
        RedisUtil.setObj(key,stu);
        Student obj = (Student)RedisUtil.getObj(key);
        System.out.println(obj.getName());
        System.out.println(obj.getSubjects().get(0).getName());



    }
}
