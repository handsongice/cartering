package cn.jy.common;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class CommonHandle {

    /**
     * Object ---> Json
     *
     * @param obj
     * @return
     */
    public static String Obj2Json(Object obj) {
        return JSONObject.toJSONString(obj);
    }

    /**
     * 将对象转换为字节
     *
     * @param obj
     * @return
     */
    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }
}
