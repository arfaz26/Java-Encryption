package com.demo.encryption;

import javax.crypto.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class EncryptionRSA {
    public static void main(String[] args){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter String to encrypt: ");
            String plainText = bufferedReader.readLine();
            String algorithm = "RSA";
            String transformation = algorithm+"/ECB/PKCS1Padding";
            KeyPair keyPairGenerator = KeyPairGenerator.getInstance(algorithm).generateKeyPair();
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE,keyPairGenerator.getPublic());
            byte [] cipherText = new byte[cipher.getOutputSize(plainText.getBytes().length)];
            int encryptedLength = cipher.update(plainText.getBytes(),0,plainText.getBytes().length,cipherText);
            cipher.doFinal(cipherText,encryptedLength);
            System.out.println("Encrypted: "+new String(cipherText));

            cipher.init(Cipher.DECRYPT_MODE,keyPairGenerator.getPrivate());
            byte [] plaintext = new byte[cipher.getOutputSize(cipherText.length)];
            int decryptedLength = cipher.update(cipherText,0,cipherText.length,plaintext);
            cipher.doFinal(plaintext,encryptedLength);
            System.out.println("Decrypted: "+new String(plaintext));
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e){
            e.printStackTrace();
        }
        catch (InvalidKeyException e){
            e.printStackTrace();
        }
        catch (BadPaddingException e){
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e){
            e.printStackTrace();
        }
        catch (ShortBufferException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
