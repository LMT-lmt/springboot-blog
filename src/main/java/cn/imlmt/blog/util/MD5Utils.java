package cn.imlmt.blog.util;

import org.springframework.util.DigestUtils;

//MD5加密类
public class MD5Utils {
    public static String code(String str){
        String digestAsHex = DigestUtils.md5DigestAsHex(str.getBytes());
        return digestAsHex;
    }
}
