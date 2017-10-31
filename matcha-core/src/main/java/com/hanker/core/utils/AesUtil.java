package com.hanker.core.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by jinhui on 2017/10/31.
 */

public class AesUtil {


    static byte[] getIV(String key) {
        String iv = key; // IV length: must be 16 bytes long
        return iv.getBytes();
    }

    static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
    // static final String CIPHER_ALGORITHM_CBC = "AES/CBC/NoPadding";
    static final String KEY_ALGORITHM = "AES";

    static Cipher cipher;
    static SecretKey secretKey;

    public static void main(String args[]) {
        String sKey = "6106c34e2786d852e79e6bf32ab8fa9b";
        String sLv = "00e3d201c5c2ac23f8154860272ba0c4";

        // 需要加密的字串
        String cSrc = "1234567890";
        // 加密
        String enString;
        try {
            enString = AesUtil.Encrypt(cSrc, sKey, sLv);

            System.out.println("加密后的字串是：" + enString);

            // 解密
            String DeString = AesUtil.Decrypt(enString, sKey, sLv);
            System.out.println("解密后的字串是：" + DeString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String doEncrypt(String str) {
        String sKey = "6106c34e2786d852e79e6bf32ab8fa9b";
        String sLv = "00e3d201c5c2ac23f8154860272ba0c4";
        return Encrypt(str, sKey, sLv);
    }

    // 加密 skey和slv为不同值 base64转换
    public static String Encrypt(String str, String sKey, String sLv) {
        try {
            if (cipher == null)
                cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);

            if (secretKey == null)
                secretKey = new SecretKeySpec(StringTool.getFromHexString(sKey), "AES");// 生成密匙

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(StringTool.getFromHexString(sLv)));// 使用加密模式初始化
            // 密钥
            byte[] encrypt = cipher.doFinal(str.getBytes("utf-8")); // 按单部分操作加密或解密数据，或者结束一个多部分操作。
            String sout = new String(Base64.encode(encrypt, Base64.DEFAULT), "UTF-8");// Base64.encodeBase64String(encrypt);
            // Logger.info("Encrypt - 加密 后：" + sout);
            return sout;
        } catch (Exception e) {
            // Logger.error("Aes Encrypt ", e);
        }
        return null;
    }

    // 解密 skey和slv为不同值 base64转换
    public static String Decrypt(String sSrc, String sKey, String sLv) {
        try {
            byte[] src = Base64.decode(sSrc, Base64.DEFAULT);// Base64.decodeBase64(sSrc);
            if (cipher == null)
                cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);

            if (secretKey == null)
                secretKey = new SecretKeySpec(StringTool.getFromHexString(sKey), "AES");// 生成密匙

            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(StringTool.getFromHexString(sLv)));// 使用解密模式初始化
            // 密钥
            byte[] decrypt = cipher.doFinal(src); // 按单部分操作加密或解密数据，或者结束一个多部分操作。
            return new String(decrypt, "utf-8");
        } catch (Exception e) {
            // Logger.error("Aes Decrypt ", e);
        }
        return null;
    }

    // 加密 skey和slv为同一值 16进制转换
    public static String Encrypt(String str, String sKey) {
        try {
            if (cipher == null)
                cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);

            if (secretKey == null)
                secretKey = new SecretKeySpec(sKey.getBytes(), "AES");// 生成密匙

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(getIV(sKey)));// 使用加密模式初始化
            // 密钥
            byte[] encrypt = cipher.doFinal(str.getBytes("utf-8")); // 按单部分操作加密或解密数据，或者结束一个多部分操作。

            String sout = StringTool.makeHexString(encrypt);
            // Logger.info("Encrypt - 加密 后：" + sout);
            return sout;
        } catch (Exception e) {
            // Logger.error("Aes Encrypt ", e);
        }
        return null;
    }

    // 解密 skey和slv为同一值 16进制转换
    public static String Decrypt(String sSrc, String sKey) {
        try {
            byte[] src = StringTool.getFromHexString(sSrc);

            if (cipher == null)
                cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);

            // KeyGenerator 生成aes算法密钥
            if (secretKey == null)
                secretKey = new SecretKeySpec(sKey.getBytes(), "AES");// 生成密匙

            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(getIV(sKey)));// 使用解密模式初始化
            // 密钥
            byte[] decrypt = cipher.doFinal(src); // 按单部分操作加密或解密数据，或者结束一个多部分操作。
            return new String(decrypt, "utf-8");
        } catch (Exception e) {
            // Logger.error("Aes Decrypt ", e);
        }
        return null;
    }


}
