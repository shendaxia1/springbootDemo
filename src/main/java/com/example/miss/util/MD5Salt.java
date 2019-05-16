package com.example.miss.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Salt {
    public static String md5(String password, String salt) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：即使是相同的密码，加不同的盐后也会生成不同密码
        ByteSource byteSalt = ByteSource.Util.bytes(salt);//这个地方的盐值只能是String类型的才能进行转化
        //密码
        Object source = password;
        //加密次数
        int hashIteration = 1024;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIteration);
        return result.toString();
    }
}
