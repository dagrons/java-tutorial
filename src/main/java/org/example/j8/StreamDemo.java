package org.example.j8;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 如何才能让一个stream的教程有趣呢，设计一个什么问题才Z能最大限度地用到stream中的所有知识点呢？
 * 用xml作为dataSource, 然后用stream api来实现各种操作吧
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


    /**
     * command 就是当前component的req object
     */
    @Value
    @Builder
    @With
    class UpdatePeopleCommand {

    }


    @Value
    @Builder
    @With
    class SavePeopleCommand {

    }

    @Value
    @Builder
    @With
    class DeletePeopleCommand {

    }

    /**
     * 设计接口，然后面向接口编程
     */
    interface PeopleRepository {
        List<Person> findAll();
        Optional<Person> findBy(final Map<String, String> params);
        Person update(final UpdatePeopleCommand updatePeopleCommand);
        Person save(final SavePeopleCommand savePeopleCommand);
        void deleteBy(final DeletePeopleCommand deletePeopleCommand);
    }

    class PeopleRepositoryImpl implements PeopleRepository {

        @Override
        public List<Person> findAll() {
            return null;
        }

        @Override
        public Optional<Person> findBy(Map<String, String> params) {
            return Optional.empty();
        }

        @Override
        public Person update(UpdatePeopleCommand updatePeopleCommand) {
            return null;
        }

        @Override
        public Person save(SavePeopleCommand savePeopleCommand) {
            return null;
        }

        @Override
        public void deleteBy(DeletePeopleCommand deletePeopleCommand) {
        }
    }


    public static void main(String[] args) throws IOException {

        InputStream resourceAsStream = StreamDemo.class.getResourceAsStream("/person.json");
        byte[] bytes = resourceAsStream.readAllBytes();
        List<Person> people = JSONObject.parseArray(new String(bytes), Person.class);
        List<String> collect = people.stream().filter(t -> t.getAge() < 60).map(t -> t.getFirstName() + t.getLastName()).collect(Collectors.toList());
        System.out.println(collect);
    }
}

