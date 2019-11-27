package com.ljm.reactor.operators;

import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * ██╗  ██╗██╗    ██╗ ██████╗
 * ██║  ██║██║    ██║██╔════╝
 * ███████║██║ █╗ ██║██║  ███╗
 * ██╔══██║██║███╗██║██║   ██║
 * ██║  ██║╚███╔███╔╝╚██████╔╝
 * ╚═╝  ╚═╝ ╚══╝╚══╝  ╚═════╝
 * Created by huwenguang on 2019/11/26.
 */
public class GroupByTest {
    private static class Person {
        private String name;
        //性别：0为男，1为女，2位Unkown
        private int gender;

        public Person(String name, int gender) {
            this.name = name;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public int getGender() {
            return gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    private static class MyJson{
        Map<String, String> mapInternal = new HashMap<>();
        public MyJson put(String key, String value){
            mapInternal.put(key, value);
            return this;
        }
        public String get(String key){
            String result = "";
            if(mapInternal.containsKey(key)){
                result = mapInternal.get(key);
            }
            return result;
        }
    }

    public void simulateMyJson(Mono<MyJson> myJson){

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFlux() throws Exception {
        Flux<String> fluxNames = Flux.just("Alice", "David" );

        StepVerifier.create(fluxNames)
                .expectNext("Alice")
                .expectNext("David")
                .expectComplete()
                .verify();
    }

    @Test
    public void groupStartCaseSensitive() throws Exception {
        Flux<String> fluxNames = Flux.just("Alice", "David", "Albert", "Sean",  "Didi", "Sandy", "dell");
        Flux<String> result = fluxNames.groupBy(name -> name.charAt(0))
                .concatMap(groupFlux -> groupFlux.startWith("Group " + groupFlux.key()));

        StepVerifier.create(result)
                .expectNext("Group A", "Alice", "Albert")
                .expectNext("Group D", "David", "Didi")
                .expectNext("Group S", "Sean", "Sandy")
                .expectNext("Group d", "dell")
                .expectComplete()
                .verify();
    }

    @Test
    public void groupStartInCaseSensitive() throws Exception {

    }

    @Test
    public void groupStartHasEmpty() throws Exception {
        Flux<String> fluxNames = Flux.just("Alice", "David", "Albert", null, "Sean",  "Didi", "Sandy", "dell");

    }

    @Test
    public void groupStartHasNull() throws Exception {
        Flux<String> fluxNames = Flux.just("Alice", "David", "Albert", null, "Sean",  "Didi", "Sandy", "dell");

    }
}