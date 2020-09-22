package com.cictec.web.auth.util;

/**
 * Commonutil类，提供一些通用的方法.
 *
 * @Project GJDD-SRV
 * @author yuanxiaofei(yuanxiaofei@cictec.cn)
 * @since 2013-4-15
 * @version 1.0
 * @change_log 
 *    	<pre>
 *			[2013-4-15 by yuanxiaofei] Initialize class,增加分页得到起始行与结束行方法.
 *		</pre>
 */
public class CommonUtil {  
    /**
     * 加密算法
     * @param pwd
     * @return
     */
    public static String getEncryptPwd(String pwd){
        String pwd_temp = "";
        String pwd_temp1 = "";
        if (null != pwd && !"".equals(pwd)) {
            pwd_temp = Encrypter.md5Encrypt(pwd);
            pwd_temp1 = Encrypter.md5Encrypt(pwd_temp);
        }
        return pwd_temp1;
    }
}
