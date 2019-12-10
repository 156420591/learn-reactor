package com.ljm.reactor.javautil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * ¨€¨€¨[  ¨€¨€¨[¨€¨€¨[    ¨€¨€¨[ ¨€¨€¨€¨€¨€¨€¨[
 * ¨€¨€¨U  ¨€¨€¨U¨€¨€¨U    ¨€¨€¨U¨€¨€¨X¨T¨T¨T¨T¨a
 * ¨€¨€¨€¨€¨€¨€¨€¨U¨€¨€¨U ¨€¨[ ¨€¨€¨U¨€¨€¨U  ¨€¨€¨€¨[
 * ¨€¨€¨X¨T¨T¨€¨€¨U¨€¨€¨U¨€¨€¨€¨[¨€¨€¨U¨€¨€¨U   ¨€¨€¨U
 * ¨€¨€¨U  ¨€¨€¨U¨^¨€¨€¨€¨X¨€¨€¨€¨X¨a¨^¨€¨€¨€¨€¨€¨€¨X¨a
 * ¨^¨T¨a  ¨^¨T¨a ¨^¨T¨T¨a¨^¨T¨T¨a  ¨^¨T¨T¨T¨T¨T¨a
 * Created by huwenguang on 2019/12/10.
 */
public class JavaUtils {
    /**
     * @author hwg
     */
    public static Date timeGetDateFromString(String timeval, String fmt){
        Date dateParsed = null;
        try{
            DateFormat df = new SimpleDateFormat(fmt);
            dateParsed = df.parse(timeval);
        }catch (Exception e){
        }

        return dateParsed;
    }

    /**
     * @author hwg
     */
    public static Date timeNow(){
        Date now = new Date();
        return now;
    }

    /**
     * @author hwg
     */
    public static String timeNowyyyyMMddHHmmss(){
        Date now = timeNow();
        return timeGetDateString(now, "yyyyMMddHHmmss");
    }

    /**
     * @author hwg
     */
    public static String timeGetDateString(Date thetime, String fmt){
        String result = "";
        try{
            DateFormat df = new SimpleDateFormat(fmt);
            result = df.format(thetime);
        }catch (Exception e){
        }

        return result;
    }

    /**
     * @author hwg
     */
    public static Long timeGetDateDiff(Date now, Date future, TimeUnit tu){
        Long diff = future.getTime() - now.getTime();
        return tu.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * @author hwg
     */
    public static Long timeGetDateDiffSecs(Date now, Date future){
        return timeGetDateDiff(now, future, TimeUnit.SECONDS);
    }

    /**
     * @author hwg
     */
    public static String takeRight(String strSource, int right){
        String strTakeRight = strSource;
        if(strSource != null){
            if(strSource.length() >= right){
                strTakeRight = strSource.substring(strSource.length() - right);
            }
        }
        return strTakeRight;
    }

    /**
     * @author hwg
     */
    public static String getStacktrackInfo(Throwable ex){
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter( writer );
        ex.printStackTrace( printWriter );
        printWriter.flush();
        String stackTrace = writer.toString();
        return stackTrace;
    }

    /**
     * @author hwg
     */
    public static String strRepeat(int len, char chPadding){
        int initlen = len > 0 ? len : 0;
        StringBuffer sb = new StringBuffer(initlen);
        for(int i=0; i < len; ++i){
            sb.append(chPadding);
        }
        return sb.toString();
    }

    /**
     * @author hwg
     */
    public static String trimPaddingRight(String strInput, int maxlen, char chPadding){
        String strNew = strInput==null ? "" : strInput;
        maxlen = maxlen > 0 ? maxlen : 0;

        if(strNew.length() < maxlen){
            int lenPad = maxlen - strNew.length();
            strNew = strNew + strRepeat(lenPad, chPadding);
        }else if(strNew.length() > maxlen){
            strNew = safeSubString(strNew, 0, maxlen);
        }
        return strNew;
    }

    /**
     * @author hwg
     */
    public static String safeSubString(String strInput, int startindex, int length){
        length     = length > 0 ? length : 0;
        startindex = startindex > 0 ? startindex : 0;

        try {
            if(strInput == null){
                return "";
            }
            int lenofstr = strInput.length();
            int endindex = startindex + length;

            if(startindex >= lenofstr){
                return "";
            }
            if(endindex >= lenofstr){
                return strInput.substring(startindex);
            }

            return strInput.substring(startindex, endindex);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @author hwg
     */
    public static boolean isEmptyString(String msg){
        if(null == msg){
            return true;
        }
        if(msg.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * @author hwg
     */
    public static boolean isNotEmptyString(String msg){
        return !isEmptyString(msg);
    }

    public static String getFirstNotEmpty(String... mystrs){
        String result = "";
        for(int i = 0; i < mystrs.length; ++i){
            String value = mystrs[i];
            if(isNotEmptyString(value)){
                result = value;
                break;
            }
        }

        return result;
    }

    /**
     * @author hwg
     */
    public static boolean safeStringEquals(String david, String albert){
        if( david != null && david.equals(albert)){
            return true;
        }
        return false;
    }
}
