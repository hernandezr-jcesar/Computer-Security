/*
* DESCRIPCION: Programa que cifra y descifra un mensaje
* pasado como argumento usando TripleDES, también llamado DESede
* Como proveedor se usa "BC" BouncyCastle
* El tamaño de clave sera 128 bits
* Como modo se va a usar ECB
* Como padding se va a usar PKCS#5
*/
import java.security.*;
import javax.crypto.*;
public class CifradoDescifradoClaveBinaria{
    public static void main (String args[]) throws Exception{
        if (args.length!=1){
            System.out.println("Indique texto a cifrar" + " como argumento entre comillas");
            return;
        }
        System.out.print("Generando una clase DESede...");
        KeyGenerator generadorClaves = KeyGenerator.getInstance("DESede","BC");
        generadorClaves.init(128);
        Key clave = generadorClaves.generateKey();
        System.out.println("\rClave DESede generada");
        // Crear un cifrador/descifrador
        Cipher cifrador = Cipher.getInstance("DESede/ECB/PKCS5Padding","BC");
        cifrador.init(Cipher.ENCRYPT_MODE,clave);
        // Encriptar con la clave obtenida
        byte[] texto_plano = args[0].getBytes("UTF8");
        byte[] texto_cifrado = cifrador.doFinal(
        texto_plano);
        System.out.print("Texto plano: ");
        for (int i=0;i<texto_plano.length;i++)
            System.out.print(texto_plano[i]+" ");
        System.out.print("\nTexto cifrado: ");
        for (int i=0;i<texto_cifrado.length;i++)
            System.out.print(texto_cifrado[i]+" ");
        System.out.println();
        // Reiniciar el cifrador al modo de desencriptacion y desencriptar
        cifrador.init(Cipher.DECRYPT_MODE,clave);
        byte[] texto_desencriptado = cifrador.doFinal(
        texto_cifrado);
        System.out.println("Texto desencriptado: "  + new String(texto_desencriptado,"UTF8"));
    }
}