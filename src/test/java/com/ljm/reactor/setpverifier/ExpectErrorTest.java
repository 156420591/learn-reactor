package com.ljm.reactor.setpverifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/11/26.
 */
public class ExpectErrorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void name() throws Exception {
        Flux<String> test = Flux.just("foo", "bar");
        StepVerifier.create(test).expectNext("foo").expectNext("bar").expectComplete().verify();
    }

    @Test
    public void testException() throws Exception {
        Flux<String> test = Flux.just("foo", "bar");
        Flux<String> more = Flux.error(new RuntimeException("test"));
        Flux<String> mytest = test.concatWith(more);
        StepVerifier.create(mytest)
                .expectNext("foo")
                .expectNext("bar")
                .expectError(RuntimeException.class)
                .verify();

    }
}