package com.ljm.reactor.operators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/11/29.
 */
public class FlatMapManyTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void name() throws Exception {
        // https://www.woolha.com/tutorials/project-reactor-convert-mono-list-t-to-flux-t-and-vice-versa
        // convert Mono<List<T>> to Flux<T> and versa vise
    }

    @Test
    public void monoList2Flux() throws Exception {
        List<String> lst = new ArrayList<>();
        lst.add("hello"); lst.add("world");
        Mono<List<String>> monoTest = Mono.just(lst);
        Flux<String> fluxTest = monoTest.flatMapMany(Flux::fromIterable);
        StepVerifier.create(fluxTest)
                .expectNext("hello")
                .expectNext("world")
                .expectComplete()
                .verify();
    }

    @Test
    public void flux2List() throws Exception {
        Flux<String> myflux = Flux.just("a", "b");
        Mono<List<String>> listMono = myflux.collectList();
        List<String> lstExpected = new ArrayList<>();
        lstExpected.add("a"); lstExpected.add("b");
        StepVerifier.create(listMono)
                .expectNext(lstExpected)
                .expectComplete()
                .verify();
    }

}