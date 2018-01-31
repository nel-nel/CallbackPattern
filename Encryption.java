/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CallbackPattern;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.List;
import java.util.ArrayList;
import java.util.regex.*;

/**
 *
 * @author Admin
 */
public class Encryption {

    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz.,-?!;";
    static final char[] ALPHABET_CHARS = ALPHABET.toCharArray();

    //inner class implements IEncriptable
    private class MonoEncryption implements IEncriptable {

        @Override //THE ACTUAL ENCRIPTION METHOD
        public String encrypt(String plainText, String cipher) {
            char[]map = makeCipher(cipher);//BUILD THE MAP FOR ENCRYPTION FIRST
            char[] plainTextChars = plainText.toCharArray();
            StringBuilder cipheredText = new StringBuilder();
           for (int i = 0; i < plainTextChars.length; i++) {
                for (int j = 0; j < ALPHABET_CHARS.length; j++) {
                    if (plainTextChars[i] == ALPHABET_CHARS[j]) {//find the position in alphabet
                        cipheredText.append(map[j]);//append the coresponding letter from cipher
                    }
                    
                }

            }
            
                
            return cipheredText.toString();
        }

        @Override
        public String decrypt(String cipherText, String cipher) {
          char[]map = makeCipher(cipher);//BUILD THE MAP FOR ENCRYPTION FIRST
            StringBuilder decryptedText = new StringBuilder();
            char[] cipherTextChars = cipherText.toCharArray();
            for (int i = 0; i < cipherTextChars.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (cipherTextChars[i] == map[j]) {//find the position in alphabet
                       decryptedText.append(ALPHABET_CHARS[j]);//append the coresponding letter from cipher
                    }
                    
                }

            }
            
            return decryptedText.toString();
        }
        //GET THE COUNT OF REPEATING EACH LETTER IN ALPHABET
        private int[] getCounts(char[] cipher_chars) {
            int[] cipher_repeat_letters = new int[ALPHABET_CHARS.length];
            for (int i = 0; i < ALPHABET_CHARS.length; i++) {
                for (int j = 0; j < cipher_chars.length; j++) {
                    if (ALPHABET_CHARS[i] == cipher_chars[j]) {
                        cipher_repeat_letters[i] += 1;
                    }
                }
            }
            return cipher_repeat_letters;
        }
        // remove repeating symbols acording to algorithm in readme.png    
        private char[] modifyCipher(String cipher) {
            char[] cipher_chars = cipher.toCharArray();
            int count = 0;

            for (int i = 0; i < cipher_chars.length; i++) {
                for (int j = 0; j < cipher_chars.length; j++) {
                    if (j != i) {
                        if (cipher_chars[i] == cipher_chars[j]) {
                            cipher_chars[j] = '_';
                            count++;
                        }
                    }

                }

            }
            char[] modifiedCipher = new char[cipher_chars.length - count];
            for (int i = 0, j = 0; i < cipher_chars.length; i++) {
                if (cipher_chars[i] != '_') {
                    modifiedCipher[j] = cipher_chars[i];
                    j++;
                }

            }
            return modifiedCipher;
        }
        //MAKE THE CIPHER
        private char[] makeCipher(String cipher) {
            char[] modifiedCipher = modifyCipher(cipher);
            int[] repeat_letters = getCounts(modifiedCipher);
            int[] cipher_pad_map = new int[ALPHABET_CHARS.length];
            char[] cipher_pad_map_letters = new char[ALPHABET_CHARS.length];
            //find the letters from CIPHER in alphabet and save the index (in alphabet) in cipher_pad_map
            for (int i = 0, k = 0; i < modifiedCipher.length; i++) {
                for (int j = 0; j < ALPHABET_CHARS.length; j++) {
                    if (modifiedCipher[i] == ALPHABET_CHARS[j]) {
                        cipher_pad_map[k] = j;
                        cipher_pad_map_letters[k]=ALPHABET_CHARS[j];
                       //System.out.println(cipher_pad_map_letters[k]);
                        k++;
                      
                    }

                }

            }
            // add the reamining of alphabet in reveresed order
            for (int i = repeat_letters.length - 1, k = modifiedCipher.length; i >= 0; i--) {
                if (repeat_letters[i] == 0) {
                    cipher_pad_map[k] = i; 
                    cipher_pad_map_letters[k]=ALPHABET_CHARS[i];
                    //System.out.println(cipher_pad_map_letters[k]);
                    k++;
                   
                }

            }
            
            return cipher_pad_map_letters;
        }
    }
    // 
    IEncriptable getMonoCipherMethod() { //access the inner class implementation through the interface 
        return new MonoEncryption();
    }


}
