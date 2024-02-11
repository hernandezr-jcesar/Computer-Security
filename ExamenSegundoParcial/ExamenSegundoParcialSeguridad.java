/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examensegundoparcialseguridad;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Arrays;

public class ExamenSegundoParcialSeguridad{
    public static String display(byte[] byteArray1) {
        StringBuilder stringBuilder = new StringBuilder();
        for(byte val : byteArray1) 
        {
            stringBuilder.append(String.format("%02x", val&0xff));
        }
        return stringBuilder.toString();
    }
    public ExamenSegundoParcialSeguridad(){
        try{                // Generar clave de 128 bits para AES
           KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            Key k = kg.generateKey();
            String s = "2022 Escuela Superior de Cómputo"; // Clave con al menos 16 bytes, del byte 0 al 15.
            System.out.println("Cadena para generar clave: " + s);
            k = new SecretKeySpec(s.getBytes(), 0, 16, "AES"); // Se puede guardar clave en archivo y recuperarla después.          

            String m = "Impossible decrypt. Exception in thread \"main\" java.lang.RuntimeException: Uncompilable source code.";

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //c.init(Cipher.ENCRYPT_MODE, k); // Inicializa cifrado del texto que se pasa en bytes.
            
            /////////////////////////////////////
            // Pasar de string a arreglo de bytes
            //String cad = "2ba5a25bfb69f2854563502144ca7191e03fa79f63a0665b34ffecabc6332eb970d48ce77feec237e4c22443976f9f14cbdd72608a9c86cd9412816e52b2b371657e2456e554e7c9d460b76216d164dd5d999d57662ff8da2754fab26a62d92de135498e112240cb7744fd3045b722e2c7ec454278180a5e8e31be9420823d5de63edb8e25be4ca7a";                        
            String cad = "a99643d6b8c4d3a4013da643972e68f6fc72ea36fae8c11646fcf219db1df868a2ee8c5b62788d597646607e9fe6723572c7869ce97cf66c54c7cb539518d8be";    
            byte[] ans = new byte[cad.length() / 2];
               
            System.out.println("Hex String : "+cad);
         
            for (int i = 0; i < ans.length; i++) {
                int index = i * 2;
                
                // Using parseInt() method of Integer class
                int val = Integer.parseInt(cad.substring(index, index + 2), 16);
                ans[i] = (byte)val;
            }
            System.out.println("Arreglo de enteros:");
            System.out.println(Arrays.toString(ans));
            System.out.println("Hexadecimales otra vez, para comprobar:");
            System.out.println(display(ans));
            
            System.out.println();
            c.init(Cipher.DECRYPT_MODE, k); // Iniciliza descifrado con la misma clave.
            byte[] decifrado = c.doFinal(ans);
            System.out.println("Mensaje descifrado:\n" + new String(decifrado));         
            
            
        } catch(Exception e){}
    }
    public static void main(String [] args){
        new ExamenSegundoParcialSeguridad();                
    }
}
