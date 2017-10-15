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

public class CipherMethod {//CALLER
    private IEncriptable callbackFtn;   // will be instantiated in the main ?
    CipherMethod (IEncriptable obj){
        this.callbackFtn = obj; 
    }
    
    String encryptText(String plainText, String cipher){
        return callbackFtn.encrypt(plainText, cipher);
    }
    String decryptText(String cipherText, String cipher){
        return callbackFtn.decrypt(cipherText, cipher);
    }

}

