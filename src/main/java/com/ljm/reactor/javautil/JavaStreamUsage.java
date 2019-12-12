package com.ljm.reactor.javautil;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/12/12.
 */
public class JavaStreamUsage {
    // https://dzone.com/articles/10-tips-to-handle-null-effectively
    
    public static int getMaxLength(List<String> words){
        int maxlen = words
                .stream()
                .filter(s -> s != null)
                .map(word -> word.length())
                .max(Integer::compareTo)
                .orElse(0);
        return maxlen;
    }

    public static String getLongestWord(List<String> words){
        String longestWord = words.stream()
                .filter(s -> s != null)
                .max( (one, two) -> one.length() - two.length() )
                .orElse("");

        return longestWord;
    }

    public static List<String> paddingToLongestWord(List<String> words){
        int maxlen = getMaxLength(words);
        return words.stream()
                .filter(word -> word != null)
                .map(word -> StringUtils.leftPad(word, maxlen, ' '))
                .collect(Collectors.toList());
    }
}
