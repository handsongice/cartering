package cn.jy.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Util {
    // 加密
    public static String getBase64(String str, String charset) {
        byte[] b = null;
        String s = null;
        try {
            if (charset == null) {
                b = str.getBytes();
            } else {
                b = str.getBytes(charset);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = Base64.encodeBase64String(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s, String charset) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            try {
                b = Base64.decodeBase64(s);
                if (charset == null) {
                    result = new String(b);
                } else {
                    result = new String(b, charset);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "PFJFUVVFU1RfRlBLSiBjbGFzcz0iUkVRVUVTVF9GUEtKIj4NCgk8R0hGTUM+6Iiq5aSp5L+h5oGvPC9HSEZNQz4NCgk8R0hGX05TUlNCSD4xMTExMTExMTExMTExMTExMTE8L0dIRl9OU1JTQkg+DQoJPEZLRktIWUhfRktGWUhaSD48L0ZLRktIWUhfRktGWUhaSD4NCgk8RktGRFpfRktGREg+PC9GS0ZEWl9GS0ZESD4NCgk8WEhGS0hZSF9TS0ZZSFpIPjwvWEhGS0hZSF9TS0ZZSFpIPg0KCTxYSEZEWl9YSEZESD4xMjMxMjMyMTQ1NjwvWEhGRFpfWEhGREg+DQoJPEZQWkxfRE0+NTE8L0ZQWkxfRE0+DQoJPFlGUF9ETT48L1lGUF9ETT4NCgk8WUZQX0hNPjwvWUZQX0hNPg0KCTxCWj48L0JaPg0KCTxLUFk+5byA56Wo5Lq6PC9LUFk+DQoJPEZIUj48L0ZIUj4NCgk8U0tZPjwvU0tZPg0KCTxYSFFEPjA8L1hIUUQ+DQoJPEZQUVFMU0g+aGVrYWkyMDE4MDEzMTAwOTM8L0ZQUVFMU0g+DQoJPEtQTFg+MDwvS1BMWD4NCgk8SlNISj4xOTEuNjwvSlNISj4NCgk8SEpKRT4xNjMuODwvSEpKRT4NCgk8SEpTRT4yNy44PC9ISlNFPg0KCTxCTUJfQkJIPjEyLjA8L0JNQl9CQkg+DQoJPEZQX0tKTVhTIGNsYXNzPSJGUF9LSk1YOyIgc2l6ZT0iMSI+DQoJCTxGUF9LSk1YPg0KCQkJPFNQTUM+5aSn6bit5qKoPC9TUE1DPg0KCQkJPFNNPjwvU00+DQoJCQk8U0w+MC4xNzwvU0w+DQoJCQk8R0dYSD48L0dHWEg+DQoJCQk8SkxEVz48L0pMRFc+DQoJCQk8U1BTTD4xPC9TUFNMPg0KCQkJPFNQREo+MTYzLjg8L1NQREo+DQoJCQk8U1BKRT4xNjMuODwvU1BKRT4NCgkJCTxGUEhYWj4wPC9GUEhYWj4NCgkJCTxIU0pCWj4wPC9IU0pCWj4NCgkJCTxTRT4yNy44PC9TRT4NCgkJCTxTUEJNPjEwMTAxMDEwMzAwMDAwMDAwMDA8L1NQQk0+DQoJCQk8WlhCTT48L1pYQk0+DQoJCQk8WUhaQ0JTPjA8L1lIWkNCUz4NCgkJCTxMU0xCUz48L0xTTEJTPg0KCQkJPFpaU1RTR0w+PC9aWlNUU0dMPg0KCQkJPEtDRT48L0tDRT4NCgkJPC9GUF9LSk1YPg0KCTwvRlBfS0pNWFM+DQo8L1JFUVVFU1RfRlBLSj4=";
        //System.out.println(Base64Util.getFromBase64(str,"UTF-8"));
        String str1 = "<REQUEST_FPKJ class=\"REQUEST_FPKJ\">\n" +
                "\t<GHFMC>航天信息</GHFMC>\n" +
                "\t<GHF_NSRSBH>111111111111111111</GHF_NSRSBH>\n" +
                "\t<FKFKHYH_FKFYHZH></FKFKHYH_FKFYHZH>\n" +
                "\t<FKFDZ_FKFDH></FKFDZ_FKFDH>\n" +
                "\t<XHFKHYH_SKFYHZH></XHFKHYH_SKFYHZH>\n" +
                "\t<XHFDZ_XHFDH>12312321456</XHFDZ_XHFDH>\n" +
                "\t<FPZL_DM>51</FPZL_DM>\n" +
                "\t<YFP_DM></YFP_DM>\n" +
                "\t<YFP_HM></YFP_HM>\n" +
                "\t<BZ></BZ>\n" +
                "\t<KPY>开票人</KPY>\n" +
                "\t<FHR></FHR>\n" +
                "\t<SKY></SKY>\n" +
                "\t<XHQD>0</XHQD>\n" +
                "\t<FPQQLSH>sahongzhi201801310094</FPQQLSH>\n" +
                "\t<KPLX>0</KPLX>\n" +
                "\t<JSHJ>191.6</JSHJ>\n" +
                "\t<HJJE>163.8</HJJE>\n" +
                "\t<HJSE>27.8</HJSE>\n" +
                "\t<BMB_BBH>12.0</BMB_BBH>\n" +
                "\t<FP_KJMXS class=\"FP_KJMX;\" size=\"1\">\n" +
                "\t\t<FP_KJMX>\n" +
                "\t\t\t<SPMC>大鸭梨</SPMC>\n" +
                "\t\t\t<SM></SM>\n" +
                "\t\t\t<SL>0.17</SL>\n" +
                "\t\t\t<GGXH></GGXH>\n" +
                "\t\t\t<JLDW></JLDW>\n" +
                "\t\t\t<SPSL>1</SPSL>\n" +
                "\t\t\t<SPDJ>163.8</SPDJ>\n" +
                "\t\t\t<SPJE>163.8</SPJE>\n" +
                "\t\t\t<FPHXZ>0</FPHXZ>\n" +
                "\t\t\t<HSJBZ>0</HSJBZ>\n" +
                "\t\t\t<SE>27.8</SE>\n" +
                "\t\t\t<SPBM>1010101030000000000</SPBM>\n" +
                "\t\t\t<ZXBM></ZXBM>\n" +
                "\t\t\t<YHZCBS>0</YHZCBS>\n" +
                "\t\t\t<LSLBS></LSLBS>\n" +
                "\t\t\t<ZZSTSGL></ZZSTSGL>\n" +
                "\t\t\t<KCE></KCE>\n" +
                "\t\t</FP_KJMX>\n" +
                "\t</FP_KJMXS>\n" +
                "</REQUEST_FPKJ>";

        String str2 = "123456";
        //System.out.println(Base64Util.getBase64(str2, "utf-8"));
        //System.out.println(Base64Util.getFromBase64(Base64Util.getBase64(str2, "utf-8"), "utf-8"));

        String test = "eyJJTlZSRVNVTFQiOlt7IkRKSCI6IjEwMTA4LTE4MTAxMS0wMDAwMDEuMSIsIktQSkciOiIwIiwiRlBETSI6IiIsIkZQSE0iOiIiLCJLSlNKIjoiIiwiU0JZWSI6Ir+qvt/Kp7Dco7q/qr7fyqew3NSt0vI6t9bA4LHgwuuyu7/J08OjoSIsIllGUERNIjoiIiwiWUZQSE0iOiIifSx7IkRKSCI6IjEwMTA4LTE4MTAxMS0wMDAwMDEuMiIsIktQSkciOiIxIiwiRlBETSI6IjM3MDIxNDE2MjAiLCJGUEhNIjoiMDAxODMyNzciLCJLSlNKIjoiMjAxOC0xMC0xMCAxMjowMzoyOSIsIlNCWVkiOiIiLCJZRlBETSI6IiIsIllGUEhNIjoiIn0seyJESkgiOiIxMDEwOC0xODEwMTEtMDAwMDAxLjMiLCJLUEpHIjoiMSIsIkZQRE0iOiIzNzAyMTQxNjIwIiwiRlBITSI6IjAwMTgzMjc4IiwiS0pTSiI6IjIwMTgtMTAtMTAgMTI6MDM6MzUiLCJTQllZIjoiIiwiWUZQRE0iOiIiLCJZRlBITSI6IiJ9LHsiREpIIjoiMTAxMDgtMTgxMDExLTAwMDAwMS40IiwiS1BKRyI6IjEiLCJGUERNIjoiMzcwMjE0MTYyMCIsIkZQSE0iOiIwMDE4MzI3OSIsIktKU0oiOiIyMDE4LTEwLTEwIDEyOjAzOjQxIiwiU0JZWSI6IiIsIllGUERNIjoiIiwiWUZQSE0iOiIifSx7IkRKSCI6IjEwMTA4LTE4MTAxMS0wMDAwMDEuNSIsIktQSkciOiIxIiwiRlBETSI6IjM3MDIxNDE2MjAiLCJGUEhNIjoiMDAxODMyODAiLCJLSlNKIjoiMjAxOC0xMC0xMCAxMjowMzo0NyIsIlNCWVkiOiIiLCJZRlBETSI6IiIsIllGUEhNIjoiIn0seyJESkgiOiIxMDEwOC0xODEwMTEtMDAwMDAxLjYiLCJLUEpHIjoiMSIsIkZQRE0iOiIzNzAyMTQxNjIwIiwiRlBITSI6IjAwMTgzMjgxIiwiS0pTSiI6IjIwMTgtMTAtMTAgMTI6MDM6NTMiLCJTQllZIjoiIiwiWUZQRE0iOiIiLCJZRlBITSI6IiJ9LHsiREpIIjoiMTAxMDgtMTgxMDExLTAwMDAwMS43IiwiS1BKRyI6IjEiLCJGUERNIjoiMzcwMjE0MTYyMCIsIkZQSE0iOiIwMDE4MzI4MiIsIktKU0oiOiIyMDE4LTEwLTEwIDEyOjA0OjA1IiwiU0JZWSI6IiIsIllGUERNIjoiIiwiWUZQSE0iOiIifSx7IkRKSCI6IjEwMTA4LTE4MTAxMS0wMDAwMDEuOCIsIktQSkciOiIxIiwiRlBETSI6IjM3MDIxNDE2MjAiLCJGUEhNIjoiMDAxODMyODMiLCJLSlNKIjoiMjAxOC0xMC0xMCAxMjowNDoxMSIsIlNCWVkiOiIiLCJZRlBETSI6IiIsIllGUEhNIjoiIn0seyJESkgiOiIxMDEwOC0xODEwMTEtMDAwMDAxLjkiLCJLUEpHIjoiMSIsIkZQRE0iOiIzNzAyMTQxNjIwIiwiRlBITSI6IjAwMTgzMjg0IiwiS0pTSiI6IjIwMTgtMTAtMTAgMTI6MDQ6MTYiLCJTQllZIjoiIiwiWUZQRE0iOiIiLCJZRlBITSI6IiJ9XX0=";
        System.out.println(Base64Util.getFromBase64(test, "gb2312"));

    }
}
