package com.example.food.common;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 前后端数据交互工具
 * 对返回前端的数据进行封装
 */
public class ReturnCodeUtil {
    private Object data;//返回的数据
    private String message;//返回的信息
    private Boolean result;//true 请求成功  false 请求失败
    private int total;//总共有多少条数据

    public Object getData() {
        return data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setData(Object data) {
        if(data instanceof List){
            List list = (List)data;
            this.total = list.size();
        }
        this.data = data;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
