
package examensegundoparcialseguridad;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class AESsimetrico{
    public static String display(byte[] byteArray1) {
        StringBuilder stringBuilder = new StringBuilder();
        for(byte val : byteArray1) 
        {
            stringBuilder.append(String.format("%02x", val&0xff));
        }
        return stringBuilder.toString();
    }
    public AESsimetrico(){
         try{                // Generar clave de 128 bits para AES
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            Key k = kg.generateKey();
            String s = "2022 Escuela Superior de Cómputo"; // Clave con al menos 16 bytes, del byte 0 al 15.
            System.out.println("Cadena para generar clave: " + s);
            k = new SecretKeySpec(s.getBytes(), 0, 16, "AES"); // Se puede guardar clave en archivo y recuperarla después.


            String m = "Hola buenos dias, buenas tardes, buenas noches...";



            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, k); // Inicializa cifrado del texto que se pasa en bytes.
            
            byte[] gb = c.doFinal(m.getBytes());
            System.out.println("\nMensaje cifrado:"); // Muestra byte por byte texto en hexadecimal.                         
            System.out.println(display(gb));
            System.out.println(Arrays.toString(gb));
            System.out.println();
            
            c.init(Cipher.DECRYPT_MODE, k); // Iniciliza descifrado con la misma clave.
            byte[] d = c.doFinal(gb);
            System.out.println("\nMensaje descifrado:\n" + new String(d));
        } catch(Exception e){}
    }
    public static void main(String [] args){
        new AESsimetrico();
    }
}
