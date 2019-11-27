package com.ljm.reactor.factory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
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
public class FromCallableTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    protected void mysleep(long millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
        }
    }

    protected String ipsGetString(boolean bflag){
        mysleep(200);

        if(bflag){
            return "ok";
        }else {
            return "failed";
        }
    }

    protected Mono<String> getFromNetwork(boolean bflag){

        //region how to use fromCallable
        /***
         * https://projectreactor.io/docs/core/release/reference/#faq
         * https://www.woolha.com/tutorials/project-reactor-processing-flux-in-parallel
         *
         *
         * B.1. How do I wrap a synchronous, blocking call?

         It is often the case that a source of information is synchronous and blocking. To deal with such sources in your Reactor applications, apply the following pattern:

         Mono blockingWrapper = Mono.fromCallable(() -> {
         return
         });
         blockingWrapper = blockingWrapper.subscribeOn(Schedulers.boundedElastic());

         Create a new Mono by using fromCallable.
         Return the asynchronous, blocking resource.
         Ensure each subscription happens on a dedicated single-threaded worker from Schedulers.boundedElastic()
         */
        //endregion

        Mono<String> monoResult = Mono.fromCallable( () -> {
            return ipsGetString(bflag);
        } );
        monoResult = monoResult.subscribeOn(Schedulers.elastic());
        return monoResult;
    }

    protected Mono<String> chainFilter(){
        return Mono.just("chainFilter");
    }

    protected Mono<String> myRespond(){
        return Mono.just("myRespond");
    }

    protected Mono<String> respAccordingIpsResult(String ipsResult){
        if("ok".equalsIgnoreCase(ipsResult)){
            return chainFilter();
        }else{
            return myRespond();
        }
    }

    protected Mono<String> respAccordingMonoIps(Mono<String> monoIpsResult){
        return monoIpsResult.flatMap(result -> respAccordingIpsResult(result));
    }

    @Test
    public void testOk() throws Exception {
        Mono<String> ipsResult = getFromNetwork(true);
        Mono<String> finalResp = respAccordingMonoIps(ipsResult);
        StepVerifier.create(finalResp)
                .expectNext("chainFilter")
                .expectComplete()
                .verify();
    }


    @Test
    public void testFail() throws Exception {
        Mono<String> ipsResult = getFromNetwork(false);
        Mono<String> finalResp = respAccordingMonoIps(ipsResult);
        StepVerifier.create(finalResp)
                .expectNext("myRespond")
                .expectComplete()
                .verify();
    }
}