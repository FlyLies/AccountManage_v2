package com.ltr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer aid;
    private Integer uid;
    private Integer wid;
    private String webAccount;
    private String webPassword;

    /*加密方法*/
    public void encryptAccount(String key) throws Exception {
        /*算法*/
        String algorithm = "DES";
        /*加密类型*/
        String transformation = "DES";
        /*Cipher：密码，获取加密对象*/
        /*transformation：参数表示使用什么类型加密*/
        Cipher cipher = Cipher.getInstance(transformation);
        /*指定秘钥规则*/
        /*第一个参数表示：密钥，key的字节数组*/
        /*第二个参数表示：算法*/
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        /*对加密进行初始化*/
        /*第一个参数：表示模式，有加密模式和解密模式*/
        /*第二个参数：表示秘钥规则*/
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        /*进行加密*/
        byte[] accountBytes = cipher.doFinal(this.webAccount.getBytes());
        byte[] passwordBytes = cipher.doFinal(this.webPassword.getBytes());
        /*编码*/
        Base64.Encoder encoder = Base64.getEncoder();
        this.webAccount = encoder.encodeToString(accountBytes);
        this.webPassword = encoder.encodeToString(passwordBytes);
    }

    /*解密方法*/
    public void decryptAccount(String key) throws Exception {
        String algorithm = "DES";
        String transformation = "DES";
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        /*对解密进行初始化*/
        /*第一个参数：表示模式，有加密模式和解密模式*/
        /*第二个参数：表示秘钥规则*/
        cipher.init(Cipher.DECRYPT_MODE, sks);
        Base64.Decoder decoder = Base64.getDecoder();
        /*解密*/
        byte[] accountBytes = cipher.doFinal(decoder.decode(this.webAccount));
        byte[] passwordBytes = cipher.doFinal(decoder.decode(this.webPassword));
        this.webAccount = new String(accountBytes, StandardCharsets.UTF_8);
        this.webPassword = new String(passwordBytes, StandardCharsets.UTF_8);
    }

}
