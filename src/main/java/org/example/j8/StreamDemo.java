package org.example.j8;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 如何才能让一个stream的教程有趣呢，设计一个什么问题才Z能最大限度地用到stream中的所有知识点呢？
 * 用xml作为dataSource, 然后用stream api来实现各种操作吧
 * 相比mysql，stream其实还是要简单多了，因为不涉及各种复杂的索引机制
 */
public class StreamDemo {

    /**
     * enum序列化的时候一般都是直接用ENUM的名字，这里就是"MALE", "FEMALE"
     */
    enum Gender { // package-private

        MALE(0, "female"), FEMALE(2, "male");

        final int gender;
        final String description;

        Gender(int gender, String description) {
            this.gender = gender;
            this.description = description;
        }

        @Override
        public String toString() {
            return this.description;
        }
    }

    /**
     * 这里builder无法定义在non-static inner class上，因为non-static inner class其实会有一个指向out-class的指针
     * 而builder定义的方法都是static的，无法为outer-class的指针定义builder方法
     * 注意这是entity，是依赖于具体的持久层实现的，不等于domain object，domain object在很多项目中就是vo
     */
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Person { // package-private
        private String id;
        @JSONField(name = "first_name")
        private String firstName;
        @JSONField(name = "last_name")
        private String lastName;
        private String email;
        private Gender gender;
        @JSONField(name = "ip_address")
        private String ipaddr;
        private Integer age;
        private Integer salary;
        private String department; // 其实使用的最常见的类型是enum，但很多时候为了省事就用String替代了
        private String company;
    }

    public static void main(String[] args) throws IOException {

        InputStream resourceAsStream = StreamDemo.class.getResourceAsStream("/person.json");
        byte[] bytes = resourceAsStream.readAllBytes();
        List<Person> people = JSONObject.parseArray(new String(bytes), Person.class);
        // 过滤年龄小于60岁的
        System.out.println("========所有年龄小于60的========");
        List<String> collect = people.stream().filter(t -> t.getAge() < 60).map(t -> t.getFirstName() + t.getLastName()).collect(Collectors.toList());
        System.out.println(collect);
        // 计算平均年龄
        System.out.println("======计算平均年龄=========");
        DoubleSummaryStatistics collect1 = people.stream().map(t -> t.getAge()).collect(Collectors.summarizingDouble(t -> t.longValue()));
        System.out.println(collect1.getAverage());
        // 按男女分组，分别计算他们的平均年龄
        System.out.println("=======男女的平均年龄=======");
        Map<Gender, List<Person>> collect2 = people.stream().filter(t->t.getGender() != null).collect(Collectors.groupingBy(t -> t.getGender()));
        collect2.forEach((k, v) -> {
            Double collect3 = v.stream().collect(Collectors.averagingDouble(t -> t.getAge().longValue()));
            System.out.printf("%s: %f\n", k, collect3);
        });
        // 按部门分组，计算每个部门的人数
        System.out.println("======每个部门的人数=======");
        Map<String, List<Person>> collect3 = people.stream().filter(t -> t.getDepartment() != null).collect(Collectors.groupingBy(t -> t.getDepartment()));
        collect3.forEach((k, v) -> {
            Long collect4 = v.stream().collect(Collectors.counting());
            System.out.printf("%s: %d", k, collect4);
        });
    }
}

