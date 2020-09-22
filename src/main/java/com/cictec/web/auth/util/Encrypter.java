package com.cictec.web.auth.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密算法类.
 *
 * @Project GJDD-SRV
 * @author hepf
 * @since 2013-5-10
 * @version 1.0
 * @change_log 
 *    	<pre>
 *			[2013-5-10 by hepf] Initialize class.
 *		</pre>
 */
public class Encrypter {

    /**
     * md5加密算法
     * 
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String md5Encrypt(String data) {
        StringBuffer md5Value = new StringBuffer();
        if (data == null) {
            return "";
        } else {
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
            //sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
            try {
                byte[] md5Bytes = md5.digest(data.getBytes("utf-8"));
                
                for (int i = 0; i < md5Bytes.length; i++) {
                    int val = ((int) md5Bytes[i]) & 0xff;
                    if (val < 16){
                        md5Value.append("0");
                    }
                    md5Value.append(Integer.toHexString(val));
                }
                //md5Value = baseEncoder.encode(hexValue.toString().getBytes());
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return md5Value.toString();
        }
    }

    /**
     * md5测试用例
     * 
     * @param args
     */
    public static void main(String[] args) {
        String md5Value = md5Encrypt("96e79218965eb72c92a549dd5a330112");
        System.out.println(md5Value);
    }
}
