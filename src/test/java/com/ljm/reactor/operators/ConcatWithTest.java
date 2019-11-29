package com.ljm.reactor.operators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/11/29.
 */
public class ConcatWithTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void name() throws Exception {
    }

    protected void myslepp(long mills){
        try{
            Thread.sleep(mills);
        }catch (InterruptedException e){
        }
    }
    protected Mono<String> longWorkWashCloth(String color){
        Mono<String> str1 = Mono.<String>fromCallable(() -> {
            System.out.println("I'm washing cloth");
            myslepp(20);
            if("black".equalsIgnoreCase(color)){
                throw new Exception("black");
            }
            return color;
        });
        return str1;
    }

    @Test
    public void test_longWorkWashCloth() throws Exception {
        Mono<String> sut = longWorkWashCloth("black");
        StepVerifier.create(sut)
                .expectError(Exception.class)
                .verify();
    }


    protected Mono<String> longWorkDinning(String dish){
        Mono<String> str1 = Mono.fromCallable(() -> {
            System.out.println("I'm having dinner");
            myslepp(20);
            if("fish".equalsIgnoreCase(dish)){
                throw new Exception("fish");
            }
            return dish;
        });
        return str1;
    }

    protected Flux<String> dinnerThenWash(String color, String dish){
        Mono<String> strWash = longWorkWashCloth(color);
        Mono<String> strDinne = longWorkDinning(dish);
        return Flux.concat(strDinne, strWash);
    }

    @Test
    public void test_washThenDinning() throws Exception {
        Flux<String> sut = dinnerThenWash("white", "apple");
        StepVerifier.create(sut)
                .expectNext("apple")
                .expectNext("white")
                .expectComplete()
                .verify();
    }
    @Test
    public void test_washThenDinning_exception() throws Exception {
        Flux<String> sut = dinnerThenWash("black", "fish");
        StepVerifier.create(sut)
                .expectErrorMessage("fish")
                .verify();
    }

    protected void longTimeWorking(){
    }

    protected Mono<Integer> generateInt(){
        Mono<Integer> result = Mono.fromCallable( () -> {
            System.out.println("generateInt");
            longTimeWorking();
            return 1;
        } );
        return result;
    }
    protected Mono<String> generateString(){
        Mono<String> result = Mono.fromCallable( () -> {
            System.out.println("generateString");
            longTimeWorking();
            return "hello";
        } );
        return result;
    }

    @Test
    public void testConcatDifferentTypes() throws Exception {
        Mono<Integer> myint = generateInt();
        Mono<String> mystr = generateString();
        Flux.concat(myint, mystr).blockLast();
        Flux.concat(mystr, myint).subscribe();
    }



    @Test
    public void testConcat() throws Exception {
        Flux<Integer> numbers1 = Flux.range(1, 3);
        Flux<Integer> numbers2 = Flux.range(4, 2);
        Flux<Integer> result = Flux.concat(numbers1, numbers2);
        StepVerifier.create(result)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .expectComplete()
                .verify();
    }
}