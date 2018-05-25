package com.clsaa.janus.admin.service;

import com.clsaa.janus.admin.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 任贵杰
 * @version v1
 * @summary
 * @since 2018/5/24
 */
public class StreamTest {
    @Getter
    @Setter
    @ToString
    class Person{
        String id;

    }
    @Test
    public void test(){
        List<Person> people = Arrays.asList(new Person(),new Person(),new Person());
        System.out.println(people.stream().peek(p->p.setId(UUIDUtil.getUUID())).collect(Collectors.toList()));
    }
}
