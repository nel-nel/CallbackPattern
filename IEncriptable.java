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
public interface IEncriptable{
    String encrypt(String plainText, String cipher);
    String decrypt(String cipherText, String cipher);
}

