import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;
public class MiRSA implements Constantes{
    public MiRSA(){
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());
        System.out.println("Generar las claves pública y secreta:");
        try{
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(TAMANO_CLAVE_RSA, sr);
            KeyPair kp = kpg.generateKeyPair();
            System.out.println("Claves generadas.");
            System.out.print("Archivo para la clave pública?: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String archpub;
            archpub = br.readLine();
            FileOutputStream fos = new FileOutputStream(archpub);
            fos.write(kp.getPublic().getEncoded());
            fos.close();
            System.out.println("Se generó archivo con clave pública.");
            System.out.print("Archivo para la clave privada?: ");
            String archpri;
            archpri = br.readLine();
            System.out.print("Ingresar contrasenia para cifrar?: ");
            char[] pwd;
            pwd = br.readLine().toCharArray();
            byte[] sal = new byte[TAMANO_SALT_BYTES];
            sr.nextBytes(sal);
            PBEKeySpec pbeks = new PBEKeySpec(pwd);
            SecretKey sk = SecretKeyFactory.getInstance("PBEWithSHAAndTwofish-CBC").generateSecret(pbeks);
            PBEParameterSpec pbeparam = new PBEParameterSpec(sal,ITERACIONES_PBE);
            Cipher c = Cipher.getInstance("PBEWithSHAAndTwofish-CBC");
            c.init(Cipher.ENCRYPT_MODE, sk, pbeparam);
            byte[] bpri = c.doFinal(kp.getPrivate().getEncoded());
            fos = new FileOutputStream(archpri);
            fos.write(sal);
            fos.write(bpri);
            fos.close();
            System.out.println("Se generó archivo con clave privada.");
        }catch(Exception e){}
    }
    public static void main(String [] args){
        new MiRSA();
    }
}    
