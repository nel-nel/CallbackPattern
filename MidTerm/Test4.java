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
public class Test4 {
    public static void main(String[] args) {
        Encryption en = new Encryption();
        IEncriptable hidden_method = en.getMonoCipherMethod();
        String cipher = "aaa";
        String plainText = "djlkdj";
        CipherMethod callBack = new CipherMethod(hidden_method);
        String encryptText = callBack.encryptText(plainText, cipher);
        
    }
}








