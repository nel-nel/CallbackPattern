/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CallbackPattern;

/**
 *
 * @author Admin
 */
public class Test {
    public static void main(String[] args) {
        Encryption en = new Encryption();
        //en.getMonoCipherMethod(); gets an implementation of IEncriptable
        IEncriptable hiddenIEncriptable = en.getMonoCipherMethod(); //hiddenIEcriptable is a inner class of the Encryption class which is instantiated with getMonoCipherMethod()of the outer class
        String cipher = "feather";
        String plainText = "djlkdj";
        //Caller 
        CipherMethod callBack = new CipherMethod(hiddenIEncriptable);
        String encryptText = callBack.encryptText(plainText, cipher);
      }
}








