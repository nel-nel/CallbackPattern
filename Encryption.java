/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CallbackPattern;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Encryption {

    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    static final char[] ALPHABET_CHARS = ALPHABET.toCharArray();

    //inner class implements IEncriptable
    private class MonoEncryption implements IEncriptable {

        @Override
        public String encrypt(String plainText, String cipher) {
            int[]map = makeCipher(cipher);
            char[] plainTexChars = plainText.toCharArray();
            int[] plainTextAlphabeticalIndexes = new int[plainTexChars.length];
            StringBuilder cipheredText = new StringBuilder();
            //find index of every letter of plainText in the alphabet
            for (int i = 0; i < plainTexChars.length; i++) {
                for (int j = 0; j < ALPHABET_CHARS.length; j++) {
                    if (plainTexChars[i] == ALPHABET_CHARS[j]) {//find the position in alphabet
                        plainTextAlphabeticalIndexes[i] = j; //the value at i-th position is an index of the alphabet
                        cipheredText.append(ALPHABET_CHARS[map[j]]);
                    }

                }

            }
            
                
            return cipheredText.toString();
        }

        @Override
        public String decrypt(String cipherText, String cipher) {
            return "decrypted text";
        }

        private int[] getCounts(char[] cipher_chars) {
            int[] cipher_repeat_letters = new int[26];
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < cipher_chars.length; j++) {
                    if (ALPHABET_CHARS[i] == cipher_chars[j]) {
                        cipher_repeat_letters[i] += 1;
                    }
                }
            }
            return cipher_repeat_letters;
        }

        private char[] modifyCipher(String cipher) {
            char[] cipher_chars = cipher.toCharArray();
            int count = 0;

            for (int i = 0; i < cipher_chars.length; i++) {
                for (int j = 0; j < cipher_chars.length; j++) {
                    if (j != i) {
                        if (cipher_chars[i] == cipher_chars[j]) {
                            cipher_chars[j] = '-';
                            count++;
                        }
                    }

                }

            }
            //System.err.println("cipher_chars: "+cipher_chars[1]);
            char[] modifiedCipher = new char[cipher_chars.length - count];
            for (int i = 0, j = 0; i < cipher_chars.length; i++) {
                if (cipher_chars[i] != '-') {
                    modifiedCipher[j] = cipher_chars[i];
                    j++;
                }

            }
            return modifiedCipher;
        }

        private int[] makeCipher(String cipher) {
            char[] modifiedCipher = modifyCipher(cipher);
            int[] repeat_letters = getCounts(modifiedCipher);
            int[] cipher_pad_map = new int[26];

            //find the letters from cipher in alphabet and save the index in cipher_pad_map
            for (int i = 0, k = 0; i < modifiedCipher.length; i++) {
                for (int j = 0; j < ALPHABET_CHARS.length; j++) {
                    if (modifiedCipher[i] == ALPHABET_CHARS[j]) {
                        cipher_pad_map[k] = j;
                        k++;
                       // System.out.println(ALPHABET_CHARS[j]);
                    }

                }

            }
            for (int i = repeat_letters.length - 1, k = modifiedCipher.length; i >= 0; i--) {
                if (repeat_letters[i] == 0) {
                    cipher_pad_map[k] = i; // add the reamining of alphabet in reveresed order
                    k++;
                    //System.out.println(ALPHABET_CHARS[i]);
                }

            }
            
            return cipher_pad_map;
        }

    }

    // 
    IEncriptable getMonoCipherMethod() { //access the inner class implementation through the interface 
        return new MonoEncryption();
    }
;

}
