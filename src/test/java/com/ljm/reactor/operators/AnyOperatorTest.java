package com.ljm.reactor.operators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/11/25.
 */
public class AnyOperatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void name() throws Exception {
    }


    @Test
    public void testObject() throws Exception {
        class Student{
            public String name;
            public int age;

            public Student(String name, int age) {
                this.name = name;
                this.age = age;
            }
        }
        Student stu_a = new Student("test", 10);
        Student stu_b = new Student("test", 8);
        List<Student> lstStudent = new ArrayList<>();
        lstStudent.add(stu_a);
        lstStudent.add(stu_b);
        Flux<Student> test = Flux.fromIterable(lstStudent);
        Predicate<Student> couldGoToSchool = student -> student.age > 9;
        Mono<Boolean> anyCouldSchool = test.any(couldGoToSchool);
        StepVerifier.create(anyCouldSchool).expectNext(true).expectComplete().verify();
    }

    @Test
    public void anyFalse() throws Exception {
        Flux<Integer> test = Flux.range(5, 2);
        Predicate<Integer> biggerThan10 = i -> i > 10;

        Mono<Boolean> monoResult = test.any(biggerThan10);
        StepVerifier.create(monoResult).expectNext(false).expectComplete().verify();
    }


    @Test
    public void anyTrue() throws Exception {
        Flux<Integer> test = Flux.range(5, 2);
        Predicate<Integer> biggerThan5 = i -> i > 5;

        Mono<Boolean> monoResult = test.any(biggerThan5);
        StepVerifier.create(monoResult).expectNext(true).expectComplete().verify();
    }
}