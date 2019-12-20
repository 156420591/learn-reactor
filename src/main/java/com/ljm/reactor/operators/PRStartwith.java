package com.ljm.reactor.operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/12/2.
 */
public class PRStartwith {
    public Mono<Integer> convertFlux2Mono(){
        //region
        /***
         *
         // https://stackoverflow.com/questions/42021559/convert-from-flux-to-mono

         How can I convert from a Flux with 1 element to a Mono?
         Flux.fromArray(arrayOf(1,2,1,1,1,2))
         .distinct()
         .take(1)
         How do I make this a Mono(1)?
         */
        //endregion

        Mono<Integer> fluxInt = Flux.just(1,2,1,1,1,2)
                .distinct()
                .take(1).singleOrEmpty();


        return fluxInt;
    }
}
