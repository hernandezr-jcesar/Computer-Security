import java.math.*;
import java.nio.charset.*;
import java.security.*;
class MiSHA256{
    public MiSHA256(){
        try{
            System.out.print("Código Hash Generado por SHA-256 para:");
            String s1 = "Instituto Politécnico Nacional";
            System.out.print("\n" + s1 + " : " + toHexString(getSHA(s1)));
            String s2 = "ESCOM";
            System.out.print("\n" + s2 + " : " + toHexString(getSHA(s2)));
            String s3 = "A1b2c3d4";
            System.out.println("\n" + s3 + " : " + toHexString(getSHA(s3)));
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Algoritmo incorrecto: " + e);
        }        
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }    
    public static String toHexString(byte[] hash){
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64){
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
    public static void main(String args[]){
        new MiSHA256();
    }
}
