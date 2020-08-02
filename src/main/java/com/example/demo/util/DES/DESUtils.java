package com.example.demo.util.DES;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESUtils {
    
    /**
     * 对字符串进行加密
     * <功能详细描述> 
     * @param str 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
    public static String getEncryptString(String str, String key){
    	try  
        {

    		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");   
    		  
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));   
      
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);   
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);   
      
            return toHexString(cipher.doFinal(str.getBytes("UTF-8"))).toUpperCase();   
        }
    	catch (Exception e)  
        {  
            throw new RuntimeException(e);
        } 
          
    }  
      
    /** 
     * 对加密字符串进行解密 
     * <功能详细描述> 
     * @param str 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
    public static String getDecryptString(String str,String key){
    	try  
        {
    		 byte[] bytesrc =convertHexString(str);      
             Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");       
             DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));      
             SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");      
             SecretKey secretKey = keyFactory.generateSecret(desKeySpec);      
             IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
                    
             cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);         
              
             byte[] retByte = cipher.doFinal(bytesrc);        
             return new String(retByte);    
        }
    	catch (Exception e)  
        {  
            throw new RuntimeException(e);  
        }
          
    }  
      

    public static byte[] convertHexString(String ss)    
    {    
	    byte digest[] = new byte[ss.length() / 2];    
	    for(int i = 0; i < digest.length; i++)    
	    {    
	    String byteString = ss.substring(2 * i, 2 * i + 2);    
	    int byteValue = Integer.parseInt(byteString, 16);    
	    digest[i] = (byte)byteValue;    
	    }    
	  
	    return digest;    
    }    
    
    public static String toHexString(byte b[]) {   
        StringBuffer hexString = new StringBuffer();   
        for (int i = 0; i < b.length; i++) {   
            String plainText = Integer.toHexString(0xff & b[i]);   
            if (plainText.length() < 2)   
                plainText = "0" + plainText;   
            hexString.append(plainText);   
        }   
           
        return hexString.toString();   
    }   
    
    public static void main(String[] args) throws Exception  
    {  
        String name ="root";  
        String password="123456";  
        String encryname = getEncryptString(name,"ZJKY8125");
        String encrypassword = getEncryptString(password,"ZJKY8125");
        System.out.println(encryname);  
        System.out.println(encrypassword);  
        System.out.println(getDecryptString(encryname,"ZJKY8125"));
        System.out.println(getDecryptString(encrypassword,"ZJKY8125"));
    }  
}
