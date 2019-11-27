package com.ljm.reactor.operators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/11/27.
 */
@RunWith(Enclosed.class)
public class FilterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws Exception {
    }

    public static class filterUsingSync{
        protected static boolean isBigger(int age){
            if(age > 3){
                return true;
            }
            return false;
        }

        @Test
        public void test() throws Exception {
            Flux<Integer> testFlux = Flux.just(2, 3, 4, 5);
            Flux<Integer> bigger = testFlux.filter(filterUsingSync::isBigger);
            StepVerifier.create(bigger)
                    .expectNext(4)
                    .expectNext(5)
                    .expectComplete()
                    .verify();
        }
    }

    public static class filterWhenUsingAsyncStream{
        protected static boolean isBigger(int age){
            if(age > 3){
                return true;
            }
            return false;
        }

        protected static Mono<Boolean> isMonobigger(int age){
            boolean bResult = isBigger(age);
            return Mono.just(bResult);
        }

        protected static Mono<Boolean> isMonoSmaller(int age){
            boolean bResult = ! isBigger(age);
            return Mono.just(bResult);
        }

        @Test
        public void test() throws Exception {
            Flux<Integer> testFlux = Flux.just(2, 3, 4, 5);
            Flux<Integer> bigger = testFlux.filterWhen(filterWhenUsingAsyncStream::isMonobigger);

            StepVerifier.create(bigger)
                    .expectNext(4)
                    .expectNext(5)
                    .expectComplete()
                    .verify();
        }
        @Test
        public void testFilterTwoPart() throws Exception {
            Flux<Integer> testFlux = Flux.just(2, 3, 4, 5).share();
            Flux<Integer> bigger = testFlux.filterWhen(filterWhenUsingAsyncStream::isMonobigger);
            Flux<Integer> smaller = testFlux.filterWhen(filterWhenUsingAsyncStream::isMonoSmaller);

            StepVerifier.create(bigger)
                    .expectNext(4)
                    .expectNext(5)
                    .expectComplete()
                    .verify();

            StepVerifier.create(smaller)
                    .expectNext(2)
                    .expectNext(3)
                    .expectComplete()
                    .verify();
        }
    }
}