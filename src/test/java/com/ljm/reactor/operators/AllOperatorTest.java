package com.ljm.reactor.operators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/11/22.
 */
public class AllOperatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws Exception {
    }
    @Test
    public void testAllResultNothing() throws Exception {
        Flux<Integer> flux = Flux.range(0, 10);
        Predicate<Integer> biggerThan10 = integer -> integer > 10;
        Mono<Boolean> monoResult = flux.all(biggerThan10);
        StepVerifier.create(monoResult).expectNext(false).expectComplete().verify();

    }

    @Test
    public void testmap() throws Exception {
        Flux<String> flux = Flux.just("A");
        flux.map(s -> "foo" + s);
        flux.subscribe(System.out::println);
        StepVerifier.create(flux).expectNext("A").expectComplete().verify();
    }
}