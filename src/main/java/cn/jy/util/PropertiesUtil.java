package cn.jy.util;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static String Properties_Value = null;

    public static String getKeyValue(String propertiesPath, String key) {
        FileInputStream in = null;
        try {
            Properties properties = new Properties();
            properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesPath));
            Properties_Value = properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取配置信息失败！");
        }
        return Properties_Value;
    }

    public static void main(String[] args) {
        //System.out.println(getKeyValue("pdfpath"));
    }

}