import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;
public class DescifraRSA implements Constantes{
    public DescifraRSA(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Solicitar archivo por descifrar y archivo de clave privada.");
            System.out.print("Para descifrar. Nombre de archivo?: ");
            String ac = br.readLine();
            if (!new File(ac).exists()){
                System.out.println(ac + " no existe. Fin.");
                return;
            }
            if (!ac.toLowerCase().endsWith(".crypto")){
                System.out.println("Los archivos cifrados poseen la extensi�n .crypto");
                return;
            }
            String ad = ac.substring(0, ac.length()-".crypto".length());
            System.out.print("Archivo con la clave privada?: ");
            String ap = br.readLine();
            System.out.print("Contrase�a utilizada para cifrar archivo " + ap + "?: ");
            char[] pwd;
            pwd = br.readLine().toCharArray();
            System.out.println("Recuperaci�n de la clave privada...");
            SecureRandom sr = new SecureRandom();
            sr.setSeed(new Date().getTime());
            FileInputStream fis = new FileInputStream(ap);
            byte[] b = new byte[TAMANO_SALT_BYTES];
            fis.read(b);
            PBEKeySpec pbeks = new PBEKeySpec(pwd);
            SecretKey sk = SecretKeyFactory.getInstance("PBEWithSHAAndTwofish-CBC").generateSecret(pbeks);
            PBEParameterSpec pbeps = new PBEParameterSpec(b,ITERACIONES_PBE);
            Cipher d = Cipher.getInstance("PBEWithSHAAndTwofish-CBC");
            d.init(Cipher.DECRYPT_MODE, sk, pbeps, sr);
            b = new byte[fis.available()];
            fis.read(b);
            b = d.doFinal(b);
            PKCS8EncodedKeySpec pkcs8eks = new PKCS8EncodedKeySpec(b);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey pk = kf.generatePrivate(pkcs8eks);
            System.out.println("Se recuper� la clave secreta.");
            DataInputStream dis = new DataInputStream(new FileInputStream(ac));
            FileOutputStream fos = new FileOutputStream(ad);
            System.out.println("Se generando archivo descifrado.");    // 1. Se recupera clave de sesi�n.
            int l = dis.readInt();
            b = new byte[l];
            dis.read(b);
            Cipher drsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");       // 2. Se descifra clave de sesi�n.
            drsa.init(Cipher.DECRYPT_MODE, pk, sr);
            b = drsa.doFinal(b);
            SecretKeySpec sks = new SecretKeySpec(b,"Blowfish");
            byte[] IV = new byte[TAMANO_IV_BYTES];                          // 3. Se recupera IV.
            dis.read(IV);
            IvParameterSpec ivps = new IvParameterSpec(IV);
            System.out.println("Se guarda " + ac + " en el archivo cifrado." + ad); // 4. Descifrar y generar archivo descifrado.
            Cipher acifra = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
            acifra.init(Cipher.DECRYPT_MODE, sks, ivps, sr);
            CipherOutputStream cos = new CipherOutputStream(fos, acifra);
            int n = dis.read();
            while (n != -1){
                cos.write(n);
                n = dis.read();
            }
            dis.close();
            cos.close();
            fos.close();
            System.out.println("OK, archivo descifrado.");
        } catch(Exception e){}
    }
    public static void main(String [] args){
        new DescifraRSA();
    }
}

