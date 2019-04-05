package cn.itcast.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 操作Redis集群版
 *
 * @author lee.siu.wah
 * @version 1.0
 * <p>File Created at 2019-03-09<p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-redis-cluster.xml")
public class Redis02Test {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /** 操作String类型(存储是二进制的字符串) */
    @Test
    public void stringTest(){

        for (int i = 1; i <= 100; i++){
            redisTemplate.boundValueOps("test" + i).set("admin" + i);
        }


        // 设置值
        //redisTemplate.boundValueOps("test").set("itcast");
        //redisTemplate.boundValueOps("date").set(new Date());

        // 获取值
        //Object value = redisTemplate.boundValueOps("test").get();
        //    System.out.println(value);
        //    Date date = (Date)redisTemplate.boundValueOps("date").get();
        //      System.out.println(date);


        // 删除key (五种数据类型通用)
        //redisTemplate.delete("test");
        //redisTemplate.delete("date");
    }


    /** 操作Set类型(多个vlaue值 无序、不能重复) */
    @Test
    public void setTest(){
        // 设置值
        redisTemplate.boundSetOps("name").add("李大华", "李中华", "李国华");
        redisTemplate.boundSetOps("name").add("李小华");

        // 获取值
        Set<Object> set = redisTemplate.boundSetOps("name").members();
        System.out.println(set);

        // 删除一个value
        redisTemplate.boundSetOps("name").remove("李小华");


        // 删除key (五种数据类型通用)
        redisTemplate.delete("name");
    }

    /** 操作List类型(多个vlaue值 有序、可以重复) */
    @Test
    public void listTest(){
        // 设置值
        // 1. 右压栈 (后添加的元素放在后面)
        redisTemplate.boundListOps("name1").rightPush("李大华");
        redisTemplate.boundListOps("name1").rightPush("李中华");
        redisTemplate.boundListOps("name1").rightPush("李小华");

        // 获取值(从左 至 右)
        // 第一个参数：开始的索引号
        // 第二个参数：结束的索引号 -1 全部
        List<Object> name1 = redisTemplate.boundListOps("name1").range(0, -1);
        System.out.println("name1: " + name1);


        // 2. 左压栈 (后添加的元素放在前面)
        redisTemplate.boundListOps("name2").leftPush("李大华");
        redisTemplate.boundListOps("name2").leftPush("李中华");
        redisTemplate.boundListOps("name2").leftPush("李小华");

        // 获取值(从左 至 右)
        List<Object> name2 = redisTemplate.boundListOps("name2").range(0, -1);
        System.out.println("name2: " + name2);

        // 根据索引号获取一个value
        Object res = redisTemplate.boundListOps("name2").index(0);
        System.out.println("res: " + res);

        // 删除指定的value
        // 第一个参数：删除的个数 0代表全部
        // 第二个参数：删除的值
        redisTemplate.boundListOps("name2").remove(0,"李小华");
        name2 = redisTemplate.boundListOps("name2").range(0, -1);
        System.out.println("name2(删除): " + name2);
        System.out.println(111);

        // 删除key (五种数据类型通用)
        redisTemplate.delete("name2");
    }

    /** 操作Hash类型(多个vlaue值 key - vlaue) */
    @Test
    public void hashTest(){
        // 设置值
        redisTemplate.boundHashOps("user").put("id", 1L);
        redisTemplate.boundHashOps("user").put("name", "admin");
        redisTemplate.boundHashOps("user").put("age", 21);

        // 获取全部的key
        Set<Object> keys = redisTemplate.boundHashOps("user").keys();
        System.out.println(keys);

        // 获取全部的value
        List<Object> values = redisTemplate.boundHashOps("user").values();
        System.out.println(values);

        // 根据指定的key获取value
        Object res = redisTemplate.boundHashOps("user").get("name");
        System.out.println(res);

        // 根据指这的key删除value
        redisTemplate.boundHashOps("user").delete("name");

        System.out.println(2);
        // 删除key (五种数据类型通用)
        //redisTemplate.delete("user");
    }


}
