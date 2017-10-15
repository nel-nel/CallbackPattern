/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MidTerm;

/**
 *
 * @author Admin
 */
public class Encryption {

 static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
 static final char[] ALPHABET_CHARS = ALPHABET.toCharArray();
 
    private class MonoEncryption implements IEncriptable {
        @Override
        public String encrypt(String plainText, String cipher) {
            return "encrypted text";
        }

        @Override
        public String decrypt(String cipherText, String cipher) {
            return "decrypted text";
        }

        private int[] getCounts(String cipher) {
            char[] cipher_chars = cipher.toCharArray(); //!!! string to char Array
            int[] cipher_repeat_letters = new int[26];           
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < cipher_chars.length; j++) {
                    if(ALPHABET_CHARS[i]==cipher_chars[j]) {
                       cipher_repeat_letters[i]+=1; 
                    }
                }
            }
            return cipher_repeat_letters;
        }

        private int[] makeCipher(String cipher) {
            int[]repeat_letters = getCounts(cipher);
            int[] cipher_pad_map = new int[26];
            StringBuilder new_key = new StringBuilder();
            //char[] cipher_chars = cipher.toCharArray();
            for (int i = 0; i < repeat_letters.length; i++) {
                if(repeat_letters[i]==1){
                    new_key.append(ALPHABET_CHARS[i]);
                }
            }
            return cipher_pad_map;
        }

    }
 
    IEncriptable getMonoCipherMethod(){ //access the inner class implementation through the interface 
        return new MonoEncryption();   
    };

}


