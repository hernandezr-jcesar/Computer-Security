import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;
public class CifraRSA implements Constantes{
    public CifraRSA(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("Para cifrar. Nombre de archivo?: ");
            String ac = br.readLine();
            if (!new File(ac).exists()){
                System.out.println(ac + " no existe. Fin.");
                return;
            }
            String archcifra = ac + ".crypto";
            System.out.print("Ingresar nombre de archivo que utiliza la clave pública:");
            String ap = br.readLine();
            FileInputStream fis = new FileInputStream(ap);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            X509EncodedKeySpec x509eks = new X509EncodedKeySpec(b);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey pk = kf.generatePublic(x509eks);
            SecureRandom sr = new SecureRandom();                   // 0. Se genera archivo cifrado.
            sr.setSeed(new Date().getTime());
            fis = new FileInputStream(ac);
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(archcifra));
            System.out.println("Generación de la clave de sesión...");     // 1. Generación de clave de sesion.
            KeyGenerator kg = KeyGenerator.getInstance("Blowfish");
            kg.init(TAMANO_CLAVE_SESION, sr);
            SecretKey sk = (SecretKey)kg.generateKey();
            System.out.println("Clave de sesion cifrada y guardada.");// 2. Guarda archivo con clave de sesion cifrada.
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.ENCRYPT_MODE, pk, sr);
            b = c.doFinal(sk.getEncoded());
            dos.writeInt(b.length);
            dos.write(b);
            byte[] bIV = new byte[TAMANO_IV_BYTES];                  // 3. Generación de IV aleatorio.
            sr.nextBytes(bIV);
            IvParameterSpec ips = new IvParameterSpec(bIV);
            dos.write(bIV);
            System.out.println("Guardando " + ac + " en el archivo cifrado..." + archcifra); // 4. Guarda archivo con datos cifrados.
            Cipher carch = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
            carch.init(Cipher.ENCRYPT_MODE, sk, ips, sr);
            CipherOutputStream cos = new CipherOutputStream(dos, carch);
            int n = fis.read();
            while (n != -1){
                cos.write(n);
                n = fis.read();
            }
            fis.close();
            cos.close();
            dos.close();
            System.out.println("Ok, archivo cifrado");
        }catch(Exception e){}
    }
    public static void main(String [] args){
        new CifraRSA();
    }
}
    
