package com.jaky.server.bean;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterFormBean {
    private String username;
    private String password;
    private String password2;
    private String birthday;
    private String email;
    private Map<String,String> errors = new HashMap();

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword2() {
        return password2;
    }
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public Map<String, String> getErrors() {
        return errors;
    }
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }


    /*
    private String username;     不能为空，且必须为2-8位字母、数字、下划线，不能以数字开头
    private String password;     不能为空，必须为3-8位的可见字符
    private String password2;    不能为空，且必须和上一次输入相同
    private String birthday;     可以为空的假设是假的，因为表单会提交一个空""，如果加上这个条件说可以为空的话，后来的字符串日期转换也会报异常，所以生日必须符合日期格式。
    private String eamil;        可以为空，否则必须是格式正确的邮箱
    */
    public boolean validate(){

        boolean isOK = true;

        if(this.username==null || this.username.trim().equals("")){
            isOK = false;
            errors.put("username", "用户名不能为空！");
        }else{
            if(!this.username.matches("[a-zA-Z_]\\w{1,7}")){
                isOK = false;
                errors.put("username", "用户名必须为2-8位字母、数字、下划线，且不能以数字开头。");
            }
        }

        if(this.password==null || this.password.trim().equals("")){
            isOK = false;
            errors.put("password", "密码不能为空！");
        }else{
            if(!this.password.matches("\\p{Graph}{3,8}")){
                isOK = false;
                errors.put("password", "密码必须为3-8位的可见字符。");
            }
        }

        if(this.password2==null || this.password2.equals("")){
            isOK = false;
            errors.put("password2", "确认密码不能为空！");
        }else{
            if(!this.password2.equals(password)){
                isOK = false;
                errors.put("password2", "两次密码必须一致！");
            }
        }

        if(this.birthday!=null){
            try {
                DateLocaleConverter dlc = new DateLocaleConverter();
                Date date = (Date) dlc.convert(this.birthday);
            } catch (Exception e) {
                isOK = false;
                errors.put("birthday", "生日必须是一个格式正确的日期，例:1990-01-10");
            }
        }

        if(this.email!=null && !this.email.equals("")){
            if(!this.email.matches("\\w+@\\w+(\\.\\w+)+")){
                isOK = false;
                errors.put("email", "邮箱格式不正确！");
            }
        }

        return isOK;
    }
}
