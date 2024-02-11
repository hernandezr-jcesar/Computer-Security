import java.io.*;
import java.security.*;
public class CreaMAC{
    public static void main(String[] args) throws Exception{
        if (args.length!=1){
            System.err.println("Ingresar archivo para calcular su MAC");
            return;
        }
        System.out.print("Ingresar passphrase:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        FileInputStream fis = new FileInputStream(args[0]);
        FileOutputStream fos = new FileOutputStream(args[0] + ".mac");
        MessageDigest md = MessageDigest.getInstance("SHA");
        DigestInputStream dis = new DigestInputStream(fis, md);
        md.update(s.getBytes());
        while (dis.read()!=-1);
        fos.write(md.digest());
        fis.close();
        fos.close();
    }
}