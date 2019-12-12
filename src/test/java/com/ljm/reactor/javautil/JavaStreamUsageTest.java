package com.ljm.reactor.javautil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

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
 * Created by huwenguang on 2019/12/12.
 */
@RunWith(Enclosed.class)
public class JavaStreamUsageTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws Exception {
    }

    public static class getMaxLength{

        @Test
        public void testEmptyList() throws Exception {
            List<String> words = new ArrayList<>();
            int maxlen = JavaStreamUsage.getMaxLength(words);
            assertEquals(0, maxlen);
        }

        @Test
        public void testListContainsNullString() throws Exception {
            String test = null;
            String first = "first";
            List<String> words = new ArrayList<>();
            words.add(test);
            words.add(first);
            int maxlen = JavaStreamUsage.getMaxLength(words);
            assertEquals(first.length(), maxlen);
        }

    }


    public static class getLongestWord{

        @Test
        public void testEmptyList() throws Exception {
            List<String> words = new ArrayList<>();
            String result = JavaStreamUsage.getLongestWord(words);
            assertEquals("", result);
        }

        @Test
        public void testListContainsNullString() throws Exception {
            String test = null;
            String first = "first";
            List<String> words = new ArrayList<>();
            words.add(test);
            words.add(first);
            words.add("sec");

            String result = JavaStreamUsage.getLongestWord(words);
            assertEquals(first, result);
        }

    }


    public static class paddingToLongestWord{

        @Test
        public void testEmptyList() throws Exception {
            List<String> words = new ArrayList<>();
            List<String> result = JavaStreamUsage.paddingToLongestWord(words);
            assertEquals(0, result.size());
            assertTrue(result.isEmpty());
        }

        @Test
        public void testListContainsNullString() throws Exception {
            String test = null;
            String first = "first";
            List<String> words = new ArrayList<>();
            words.add(test);
            words.add(first);
            words.add("sec");

            List<String> result = JavaStreamUsage.paddingToLongestWord(words);
            assertEquals(2, result.size());

            List<String> expected = new ArrayList<>();
            expected.add("first");
            expected.add("  sec");
            assertEquals(expected, result);
        }

    }

}