// MAC Message Authentication Code
// El receptor puede verificar que el archivo no fue modificado por alguien.
import java.io.*;
import java.security.*;
public class ChecaMAC{
    public static void main(String[] args) throws Exception{
        if (args.length!=1){
            System.err.println("Ingresar nombre de archivo para comprobar MAC:");
            return;
        }
        File f = new File(args[0] + ".mac");
        if (!f.exists()){
            System.err.println("No existe ese archivo .mac");
            return;
        }
        System.out.print("Ingresar passphrase: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        FileInputStream fis = new FileInputStream(args[0]);
        FileInputStream fismac = new FileInputStream(f);
        MessageDigest md = MessageDigest.getInstance("SHA");
        DigestInputStream dis = new DigestInputStream(fis,md);
        md.update(s.getBytes());
        while (dis.read()!=-1);
        byte[] bmac = new byte[fismac.available()];
        fismac.read(bmac);
        if (MessageDigest.isEqual(md.digest(), bmac)) System.out.println("MAC correcto");
        else System.out.println("MAC incorrecto");
        fis.close();
        fismac.close();
    }
}