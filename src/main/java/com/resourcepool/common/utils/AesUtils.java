package com.resourcepool.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;


/** 
 * DES安全编码组件 
 *  
 * <pre> 
 * 支持 DES、DESede(TripleDES,就是3DES)、AES、Blowfish、RC2、RC4(ARCFOUR) 
 * DES                  key size must be equal to 56 
 * DESede(TripleDES)    key size must be equal to 112 or 168 
 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
 * RC2                  key size must be between 40 and 1024 bits 
 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits 
 * 具体内容 需要关注 JDK Document http://.../docs/technotes/guides/security/SunProviders.html 
 * </pre> 
 *  
 * @author 梁栋 
 * @version 1.0 
 * @since 1.0 
 */  
public abstract class AesUtils {
    /** 
     * ALGORITHM 算法 <br> 
     * 可替换为以下任意一种算法，同时key值的size相应改变。 
     *  
     * <pre> 
     * DES                  key size must be equal to 56 
     * DESede(TripleDES)    key size must be equal to 112 or 168 
     * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
     * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
     * RC2                  key size must be between 40 and 1024 bits 
     * RC4(ARCFOUR)         key size must be between 40 and 1024 bits 
     * </pre> 
     *  
     * 在Key toKey(byte[] key)方法中使用下述代码 
     * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code> 替换 
     * <code> 
     * DESKeySpec dks = new DESKeySpec(key); 
     * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM); 
     * SecretKey secretKey = keyFactory.generateSecret(dks); 
     * </code> 
     */  
    public static final String ALGORITHM = "AES";  
  
    /** 
     * 转换密钥<br> 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    private static Key toKey(byte[] key) throws Exception {  
//        DESKeySpec dks = new DESKeySpec(key);  
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
//        SecretKey secretKey = keyFactory.generateSecret(dks);  
  
        //当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码  
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);  
  
        return secretKey;  
    }  
  
    /** 
     * 解密 
     *  
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decrypt(byte[] data, String key) throws Exception {  
        Key k = toKey(Base64.decodeBase64(key));  
  
        Cipher cipher = Cipher.getInstance(ALGORITHM);  
        cipher.init(Cipher.DECRYPT_MODE, k);  
  
        return cipher.doFinal(data);  
    }  
  
    /** 
     * 加密 
     *  
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encrypt(byte[] data, String key) throws Exception {  
        Key k = toKey(Base64.decodeBase64(key));  
        Cipher cipher = Cipher.getInstance(ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, k);  
  
        return cipher.doFinal(data);  
    }  
    
    /** 
     * 生成密钥 
     *  
     * @param seed 
     * @param keySize
     * @return 
     * @throws Exception 
     */  
    public static String initKey(String seed, int keySize) throws Exception {  
        SecureRandom secureRandom = null;  
  
        if (seed != null) {  
            secureRandom = new SecureRandom(Base64.decodeBase64(seed));  
        } else {  
            secureRandom = new SecureRandom();  
        }  
  
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);  
        kg.init(secureRandom);  
        kg.init(keySize);
  
        SecretKey secretKey = kg.generateKey();  
  
        return Base64.encodeBase64String(secretKey.getEncoded());  
    }
    
    
    public static void main(String[] args) {
    	
    	try {
    		String inputStr = "41052219950620723X";
            String key = "7FGj9cnESMq8moCPq++HI+TmtoJtA3gn6hbN7MYFXZA=";
//            String key = AesUtils.initKey(null, 256);
            System.err.println("原文:\t" + inputStr);
      
            System.err.println("密钥:\t" + key);  
      
            byte[] inputData = inputStr.getBytes("UTF-8");
            inputData = AesUtils.encrypt(inputData, key);
      
            System.err.println("加密后:\t" + Base64.encodeBase64String(inputData));  
      
            byte[] outputData = AesUtils.decrypt(inputData, key);
            String outputStr = new String(outputData);  
      
            System.err.println("解密后:\t" + outputStr);  
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
}  
