package com.ljm.reactor.operators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import static org.junit.Assert.*;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/12/2.
 */
public class PRMonoZipWithTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void name() throws Exception {
    }

    public static class monoZipWithToTuple{
        protected Mono<Void> longWorkVoid(){
            return Mono.empty();
        }

        protected Mono<String> doLongWork(){
            return Mono.fromCallable(() -> {return "test"; });
        }

        protected Mono<String> getUsernameInternal(){
            return Mono.fromCallable(() -> "username" );
        }

        protected Mono<Integer> getAgeInternal(){
            return Mono.fromCallable(() -> 1);
        }

        protected Mono<String> getUsername(){
            return doLongWork().flatMap(notused -> getUsernameInternal());
        }

        protected Mono<Integer> getAge(){
            return doLongWork().flatMap(notused -> getAgeInternal());
        }

        @Test
        public void name() throws Exception {
            Mono<String> username = getUsername();
            Mono<Integer> age = getAge();
            Mono<Tuple2<String, Integer>> sut = username.zipWith(age);
            StepVerifier.create(sut)
                    .expectNext(Tuples.of("username", 1))
                    .expectComplete()
                    .verify();
        }
    }
}