import java.security.*;
public class MiCifraMD5{
    private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
    public static String cifraMD5(String s){
        try{
           MessageDigest msgd = MessageDigest.getInstance("MD5");
           byte[] bytes = msgd.digest(s.getBytes());
           StringBuilder sb = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++){
               int l = (int)(bytes[i] & 0x0f);
               int h = (int)((bytes[i] & 0xf0) >> 4);
               sb.append(CONSTS_HEX[h]);
               sb.append(CONSTS_HEX[l]);
           }
           return sb.toString();
        } catch (NoSuchAlgorithmException e) {
           return null;
        }
    }
    public static void main(String args[]){
        System.out.println("\n012345 Cifrado MD5: '" + cifraMD5("012345")+"'");
        System.out.println("ESCOM Cifrado MD5: '" + cifraMD5("ESCOM")+"'");
    }
}