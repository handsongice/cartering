package cn.jy.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 123 on 2018-07-05
 */
public class DecodeUtil {
    private static final String CHARACTER="UTF-8";
    /**
     * 解码
     * @param base64Str
     * @return
     */
    public static Map<String,String> decode(String base64Str) throws Exception {
        // 3des加密税号长度(2) 数字字母字符串(8) 加密税号  数字字母字符串(4) 加密机器编号 数字字母字符串(5) base64加密后的3des的key 3des加密后机器编码长度(2) base64加密后的3des的key的长度(2)
        //  10aaaaaaaa1111111111bbbb2222ccccc33333333333333333333333304
        //1.str进行base64解码
        //测试用例
        //base64Str="MzI4ODg4ODg4OGdyVWdjSkkza25PbERnTnJDaDdSNXFLNkEzY05TNzUvNDQ0NEcwRE9NNTcwcGk1Qk9DeFEzWDBVQkE9PTU1NTU1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1MjQ2MA==";

        //base64Str="MTI4ODg4ODg4OFdxSnE0TEVDQXUwPTQ0NDRQTDZ2NVo2Sm1nZz01NTU1NWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNTEyNjA=";

//                        MTI4ODg4ODg4OFdxSnE0TEVDQXUwPTQ0NDRQTDZ2NVo2Sm1nZz01NTU1NWEyVjVhMlY1YTJWNWEy
//        VjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNTEyNjA=
        String Str= Base64Util.getFromBase64(base64Str,CHARACTER);

        System.out.println("base64解码后的字符串====>"+Str);
        //2.截取字符串
        //2.1 加密后税号长度(2)
        System.out.println("获取加密后税号长度"+Str.substring(0,2));
        int encodeTaxNoLen= Integer.valueOf(Str.substring(0,2));
        //2.2 获取加密税号
        String encodetTxNo=Str.substring(10,10+encodeTaxNoLen);
        //2.3 加密后机器编号长度（2）
        int encodeMachineNoLen=Integer.valueOf(Str.substring(Str.length()-4, Str.length()-2));
        //2.4 获取加密机器编号
        String encodeMachineNo=Str.substring(2+8+encodeTaxNoLen+4,2+8+encodeTaxNoLen+4   +encodeMachineNoLen);
        //2.5 获取加密后的key的长度
        int encodeKeyLen= Integer.parseInt(Str.substring(Str.length()-2,Str.length()));
        //2,6 获取加密后的key
        String encodeKey=Str.substring(Str.length()-(2+2+encodeKeyLen), Str.length()-4);
        //2.7 base64解key
        String key= Base64Util.getFromBase64(encodeKey,CHARACTER);
        System.out.println("base64解key=====>"+key);
        //3解码获取税号  机器码
        //3.1  获取税号
        String taxNo= Base64Util.getFromBase64(encodetTxNo,CHARACTER);
        System.out.println("税号"+taxNo);
        //3,.2  获取机器号
        String machineNo= Base64Util.getFromBase64(encodeMachineNo,CHARACTER);
        System.out.println("机器号"+machineNo);
        Map map=new HashMap();
        map.put("taxNo",taxNo);
        map.put("machineNo",machineNo);
        return map;
    }


    public static String encode() throws Exception {
//        String taxNo="taxNotaxNotaxNotaxNo";
//        String machineNo="machineNo";
        String taxNo="91370200783744398J";
        String machineNo="B004";
        String key="1qazmko96y%$#TRGEG$#@$%6";

        String encodeKey= Base64Util.getBase64(key,CHARACTER);
        String encodeTaxNo= Base64Util.getBase64(taxNo,CHARACTER);
        String encodeMachineNo= Base64Util.getBase64(machineNo,CHARACTER);

//        int youNumber = 1;
//        // 0 代表前面补充0
//        // 4 代表长度为4
//        // d 代表参数为正数型
//        String str = String.format("%04d", youNumber);
//        System.out.println(str); // 0001
        //记得要左补零为2位
        String encodeKeyLen=String.format("%02d", encodeKey.length());
        String encodeTaxNoLen=String.format("%02d", encodeTaxNo.length());
        String encodeMachineLen=String.format("%02d", encodeMachineNo.length());

        //拼接
        String str=encodeTaxNoLen+"88888888"+encodeTaxNo+"4444"+encodeMachineNo+"55555"+encodeKey+encodeMachineLen+encodeKeyLen;
        str= Base64Util.getBase64(str,CHARACTER);
        System.out.println(str);
        //base64编码
        return str;

    }

    public static void main(String[] args) throws Exception {
//        HashSet<String> hs=new HashSet<>();
//        hs.add("1");
//        hs.add("2");
//        hs.add("3");
//        hs.add("4");
//        hs.add("5");
//        hs.add("5");
//        for(String s:hs){
//            System.out.println(s);
//        }
        String str=encode();
        decode(str);
        /*MzI4ODg4ODg4OHhLWWZqc05tcWJSWUNXbjZCR2plaTNTdmFhZ3R3TkU4NDQ0NGZKdjFnK25RVGFr
        PTU1NTU1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJW
        NWEyVjVhMlY1MTI2MA==
        */
//        String str="MzI4ODg4ODg4OHhLWWZqc05tcWJSWUNXbjZCR2plaTNTdmFhZ3R3TkU4NDQ0NGZKdjFnK25RVGFr\n" +
//                "PTU1NTU1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJWNWEyVjVhMlY1YTJW\n" +
//                "NWEyVjVhMlY1MTI2MA==";

    }
}
