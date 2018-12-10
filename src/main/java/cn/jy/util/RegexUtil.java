package cn.jy.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 正则表达式工具类
 */
public class RegexUtil {
    /**
     * 字符串正则字母，用1替换字母,取余 返回
     *
     * @param str
     * @return
     */
    public static int charReg(String str, int dividend) {
        String charReg = "[a-zA-Z]";
        str = StringUtils.replacePattern(str, charReg, "1");
        return (int) (Long.valueOf(str) % dividend);
    }

    public static String generateFPXXTableName(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH) + 1;
        String tableName = "xxfp" + currYear + StringUtils.leftPad(currMonth + "", 2, "0");

        return tableName;
    }


    public static void main(String[] args) {
        /*String str="3333333331111111111222";//9223372036854775807
        System.out.println(Long.MAX_VALUE);
        System.out.println((int) (Long.valueOf(str) % 48));*/
        System.out.println("2222".compareTo("2222"));
    }
}
