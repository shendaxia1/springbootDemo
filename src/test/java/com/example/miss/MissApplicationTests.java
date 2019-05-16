package com.example.miss;

import com.example.miss.controller.BaseController;
import com.example.miss.controller.config.RedisService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MissApplicationTests extends BaseController {
    private JSONObject json = new JSONObject();
    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void setString() {
        redisService.set("redis_string_test", "redis_string_test111");
    }

    @Test
    public void getString() {
        String result = redisService.get("redis_string_test");
        System.out.println(result);
    }

    @Test
    public void setOject() throws IOException {
        Person person = new Person("preson1", "male");
        Person person1 = new Person("person2", "famale");
        Person person2 = new Person("person3", "male");
        List<Person> list = new ArrayList<Person>();
        list.add(person);
        list.add(person1);
        list.add(person2);
        redisService.set("redis_list_test", "aa");
    }

    @Test
    public void getList() {
        String result = redisService.get("redis_list_test");
        System.out.println(result);
    }

    class Person {
        private String name;
        private String sex;

        public Person() {

        }

        public Person(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

}
