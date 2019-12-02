package com.ljm.reactor.operators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/12/2.
 */
public class PRStartwithTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void name() throws Exception {
    }

    public static class startWithTest{
        @Test
        public void name() throws Exception {
            Flux<String> fluxOld = Flux.just("ajay", "barron");
            Flux<String> fluxAdd = fluxOld.startWith("emerson");
            StepVerifier.create(fluxAdd)
                    .expectNext("emerson")
                    .expectNext("ajay")
                    .expectNext("barron")
                    .expectComplete()
                    .verify();


            StepVerifier.create(fluxOld)
                    .expectNext("ajay")
                    .expectNext("barron")
                    .expectComplete()
                    .verify();
        }
    }

    public static class concatWithTest{
        @Test
        public void name() throws Exception {
            Flux<String> fluxOld = Flux.just("ajay");
            Flux<String> flux2Concat = Flux.just("emerson");

            Flux<String> fluxAdd = fluxOld.concatWith(flux2Concat);
            StepVerifier.create(fluxAdd)
                    .expectNext("ajay")
                    .expectNext("emerson")
                    .expectComplete()
                    .verify();


            StepVerifier.create(fluxOld)
                    .expectNext("ajay")
                    .expectComplete()
                    .verify();
        }

    }
}