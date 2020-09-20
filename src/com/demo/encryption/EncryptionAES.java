package com.demo.encryption;

import javax.crypto.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class EncryptionAES {

    public static void main(String[] args){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter string to encrypt: ");
            String plainText = bufferedReader.readLine();
            System.out.println("Plain Text: "+ plainText);
            String algorithm ="AES";

            Key key = KeyGenerator.getInstance(algorithm).generateKey();
            String transformation = algorithm+"/ECB/PKCS5Padding";
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] ciphertext= new byte[cipher.getOutputSize(plainText.getBytes().length)];
            int encryptedLength = cipher.update(plainText.getBytes(), 0, plainText.getBytes().length, ciphertext);
            cipher.doFinal(ciphertext, encryptedLength);
            System.out.println("Encrypted Cipher Text: "+new String(ciphertext));

            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] plaintext= new byte[cipher.getOutputSize(plainText.getBytes().length)];
            int decryptedLength = cipher.update(ciphertext, 0, ciphertext.length, plaintext);
            cipher.doFinal(plaintext,decryptedLength);
            System.out.println("Decrypted Plain Text: "+new String(plaintext));
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