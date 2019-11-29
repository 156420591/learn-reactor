package com.ljm.reactor.errorhandle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.nio.charset.IllegalCharsetNameException;
import java.security.InvalidAlgorithmParameterException;

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
public class DoOnErrorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void name() throws Exception {
    }

    protected Mono<Void> callMeByYourName(String name){
        if("Oliver".equalsIgnoreCase(name)){
            return Mono.error(new InvalidAlgorithmParameterException("test"));
        }
        if("Elio".equalsIgnoreCase(name)){
            return Mono.error(new IllegalCharsetNameException("test"));
        }

        return Mono.empty();
    }

    @Test
    public void test_callMeByYourName1() throws Exception {
        Mono<Void> result = callMeByYourName("others");
        StepVerifier.create(result)
                .expectComplete()
                .verify();
    }

    @Test
    public void test_callMeByYourName_expectException1() throws Exception {
        Mono<Void> result = callMeByYourName("Oliver");
        StepVerifier.create(result)
                .expectError(InvalidAlgorithmParameterException.class)
                .verify();
    }

}