package cn.jy.dto;

public class ResultMap {
    private int status; //状态码 200-成功 500-失败
    private String msg; //信息描述
    private Object data; //返回数据

    public ResultMap() {
    }

    public ResultMap(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultMap(Object data) {
        this.status = 200;
        this.msg = "ok";
        this.data = data;
    }

    public ResultMap(String msg, Object data) {
        this.status = 500;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMap{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static ResultMap fail(String msg, Object data){
        return new ResultMap(msg,data);
    }
    public static ResultMap fail(String msg){
        return new ResultMap(msg,null);
    }
    public static ResultMap build(Integer status,String msg,Object data){
        return new ResultMap(status,msg,data);
    }
    public static ResultMap success(Object data){
        return new ResultMap(data);
    }
    public static ResultMap success(String msg){
        return new ResultMap(200,msg,null);
    }
    public static ResultMap success(){
        return new ResultMap(null);
    }
}
