import java.util.Scanner;
public class MiMD5 {
    String s;
    public MiMD5(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Ingresar texto: ");
        String txt = scnr.nextLine();
        System.out.println("MD5: " + md5(txt));
        System.out.println("SHA1: " + sha1(txt));        
    }
    public String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i)
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public String md5(String txt) {
        return getHash(txt, "MD5");
    }
    public String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
    public static void main(String [] args){
        new MiMD5();
    }
}