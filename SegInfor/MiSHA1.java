import java.math.*;
import java.security.*;
public class MiSHA1 {
    public MiSHA1(){
        System.out.print("Código Hash Generado por SHA-1 para: "); 
        String s1 = "Escuela Superior de Cómputo";
        System.out.println("\n" + s1 + " : " + encriptar(s1));
        String s2 = "ESCOM";
        System.out.println("\n" + s2 + " : " + encriptar(s2));        
    }
    public static String encriptar(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) hashtext = "0" + hashtext;
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) { throw new RuntimeException(e); }
    }
    public static void main(String args[]){
        new MiSHA1();
    }
}
