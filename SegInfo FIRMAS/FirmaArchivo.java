import java.util.*;
import java.io.*;
import java.security.*;
import javax.crypto.*;
import java.security.spec.*;
import javax.crypto.spec.*;
public class FirmaArchivo implements Constantes{ // Solicitar archivos por firmar y de clave privada por utilizar
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingresar archivo por firmar:");
        String s = br.readLine();
        if (!new File(s).exists()){
            System.out.println("El archivo " + s + " no existe");
            return;
        }
        String sf = s + ".sign";
        System.out.print("Ingresar archivo que posee clave privada por utilizar: ");
        String sp = br.readLine(); // Se recupera la clave privada
        System.out.print("Ingresar contraseña con que se cifró archivo " + sf + ": ");
        String pwd = br.readLine();
        System.out.println("Se recupera clave privada...");
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());
        FileInputStream fis = new FileInputStream(sf);
        byte[] b = new byte[TAMANO_SALT_BYTES];
        fis.read(b);
        PBEKeySpec pbeks = new PBEKeySpec(pwd.toCharArray());
        SecretKey sk = SecretKeyFactory.getInstance("PBEWithSHAAndTwofish-CBC").generateSecret(pbeks);
        PBEParameterSpec pbeps = new PBEParameterSpec(b,ITERACIONES_PBE);
        Cipher cd = Cipher.getInstance("PBEWithSHAAndTwofish-CBC");
        cd.init(Cipher.DECRYPT_MODE, sk, pbeps, sr);
        b = new byte[fis.available()];
        fis.read(b);
        fis.close();
        b = cd.doFinal(b);
        PKCS8EncodedKeySpec pkcsbeks = new PKCS8EncodedKeySpec(b);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pk = kf.generatePrivate(pkcsbeks);
        System.out.println("Clave secreta recuperada");
        fis = new FileInputStream(s);          // Se firma el archivo
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(pk);
        b = new byte[1024];
        while (fis.available()>=1024){
            fis.read(b);
            sig.update(b);
        }
        int quedan = fis.available();
        fis.read(b,0,quedan);
        sig.update(b,0,quedan);
        b = sig.sign();
        fis.close();
        FileOutputStream fos = new FileOutputStream(sf);
        fos.write(b);
        fos.close();
        System.out.println("Archivo firmado, la firma se deposito en " + sf);
    }
}
