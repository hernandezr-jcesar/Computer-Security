import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;
public class ChecaFirma implements Constantes{ // Solicitar archivo por verificar y de clave publica por utilizar
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingresar archivo por verificar:");
        String sa = br.readLine();
        if (!new File(sa).exists()){
            System.out.println("El archivo " + sa + " no existe");
            return;
        }
        String sf = sa + ".sign";
        if (!new File(sf).exists()){
            System.out.println("El archivo " + sa + " no existe");
            return;
        }
        System.out.print("Ingresar archivo que posee la clave publica por utilizar: ");
        String ap = br.readLine(); // RecuperaR la clave publica
        FileInputStream fis = new FileInputStream(ap);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        fis.close();
        X509EncodedKeySpec X509EKS = new X509EncodedKeySpec(b);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey cp = kf.generatePublic(X509EKS);
        System.out.println("Clave publica recuperada");
        fis = new FileInputStream(sf);         // ComprobaR firma
        byte[] bf = new byte[fis.available()];
        fis.read(bf);
        fis.close();
        fis = new FileInputStream(sa);
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(cp);
        b = new byte[1024];
        while(fis.available()>=1024){
            fis.read(b);
            sig.update(b);
        }
        int n = fis.available();
        fis.read(b, 0, n);
        sig.update(b, 0, n);
        fis.close();
        if (sig.verify(bf)) System.out.println("El archivo firmado está correcto");
        else System.out.println("La firma no es correcta");
    }
}