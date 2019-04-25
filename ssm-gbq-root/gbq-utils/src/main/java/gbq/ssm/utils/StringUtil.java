package gbq.ssm.utils;

import java.net.URL;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 * 
 * @ClassName: StringUtil
 * @author xph
 * @date 2018年11月2日
 */
public class StringUtil {

    public static final String EMPTY = "";

    public static String trimExtension(String name) {
        int pos = name.indexOf('.');
        if (-1 != pos) {
            return name.substring(0, pos);
        }
        return name;
    }

    public static String trimURI(String uri) {
        String trimmed = uri.substring(1);
        int splashIndex = trimmed.indexOf('/');
        return trimmed.substring(splashIndex);
    }

    public static String MD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    public static String dotToSplash(String name) {
        return name.replaceAll("\\.", "/");
    }

    public static String getRootPath(URL url) {
        System.err.println(url);
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf('!');
        if (-1 == pos) {
            return fileUrl;
        }
        return fileUrl.substring(5, pos);
    }
    
    /**
     * 日期转换
     * 
     * @param date
     * @return
     */
    public static String dateToStringToS(Date date) {
        if (null != date) {
            SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdm.format(date);
        } else {
            return "-";
        }
    }
    
    public static String dateToStringToSs(Date date) {
        if (null != date) {
            SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM");
            return sdm.format(date);
        } else {
            return "-";
        }
    }
    
    public static String dateToStringWithoutTime(Date date) {
        if (null != date) {
            SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
            return sdm.format(date);
        } else {
            return "";
        }
    }
    
    public static Date stringToDate(String date) throws ParseException {
        if(null != date) {
            SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdm.parse(date);
        }
        return null;
    }
    
    public static Date stringToDateMM(String date) throws ParseException {
        if(null != date) {
            SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM");
            return sdm.parse(date);
        }
        return null;
    }
    /**
     * int时间toString
    * @Title: intTimeToString
    * @param @param timestamp
    * @param @return
     */
    public static String intTimeToString(Integer timestamp) {
        long nowTimeLong=new Long(timestamp).longValue()*1000;
        Date date = new Date(nowTimeLong);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
    
}
