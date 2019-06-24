package com.reids;

import com.redis.entity.Student;
import com.redis.entity.Subject;
import com.redis.util.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class TestRedis {


    public static void main(String[] args) {
       /* RedisUtil.set("name", "张三aa");
        System.out.println(RedisUtil.get("name"));*/
        //testSeri();

        //ExecutorService exe = Executors.newFixedThreadPool(5);//ThreadPoolExecutor
        //exe.execute(new ThreadDemo());
       /* ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 100, 200, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(5));
        for (int i = 0; i < 1000 ; i++) {
            executor.execute(new ThreadDemo() );
        }*/
        for (int i = 0; i < 2 ; i++) {
            new Thread(new ThreadDemo(),"thread"+i).start();
        }
    }

    public static void testSeri(){
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
class ThreadDemo extends Thread{
    @Override
    public void run() {
        Jedis jedis = RedisUtil.getJedis();
        System.out.println(Thread.currentThread().getName()+"  "+RedisUtil.get("name"));
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RedisUtil.returnResource(jedis);
    }
}