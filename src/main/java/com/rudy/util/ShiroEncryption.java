package com.rudy.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroEncryption {
    /**
     * 加盐加密方法
     * @param algorithmName 加密的方式如"MD5"
     * @param source 加密对象
     * @param salt 加密盐值
     * @param hashIterations 加密次数
     * @return
     */
    public static String encryption(String algorithmName, String source,String salt, int hashIterations){
        /*
           该构造方法有4个参数
           algorithmName为加密的方式如"MD5"
           source为要加密的东西
           salt为加密盐值
           hashInteration为加密次数
           public SimpleHash(String algorithmName, Object source, Object salt, int hashIterations)
         */
        SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
        return simpleHash.toString();
    }
//    //密码加密测试
//    public static void main(String[] args) {
//        String pwd = encryption("MD5","123123","jessica",10);
//        System.out.println(pwd);
//    }
}
