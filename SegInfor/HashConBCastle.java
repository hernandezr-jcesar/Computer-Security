import java.security.*;
import java.io.*;
import java.util.Scanner;
public class HashConBCastle{
    public HashConBCastle(){
        Scanner scnr = new Scanner(System.in);
        String s = scnr.nextLine();
        try{
        if (s.length() != 1) {
            mensajeAyuda();
            System.exit(1);
        }
        Security.addProvider(new BouncyCastleProvider()); // proveedor BC
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] b = new byte[1000];
        FileInputStream in = new FileInputStream(s);
        int leidos = in.read(b, 0, 1000);
        while (leidos != -1) {
            messageDigest.update(b);
            leidos = in.read(b, 0, 1000);
        }
        in.close();
        byte[] res = messageDigest.digest();
        System.out.println("RESUMEN:");
        mostrarBytes(res);
        System.out.println();
        }catch(Exception e){}
    }
    public static void mostrarBytes(byte [] b) {
        System.out.write(b, 0, b.length);
    } 
    public static void mensajeAyuda() {
        System.out.println("Generar Hash");
        System.out.println("Sintaxis: java HashConBCastle archivo");
    }
    public static void main(String [] args){
        new HashConBCastle();
    }
}
