import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;
import java.security.spec.*;
import java.math.BigInteger;
public class ServidorSkip{
	public static void main(String[] args) throws Exception{
		if (args.length!=1){ // El servidor empieza a aceptar conexiones
			System.err.println("Ingresar un puerto como argumento");
			return;
		}
		ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
		System.out.println("Ocioso, esperando conexiones...");
		Socket s = ss.accept();
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    // Crear par de clave privada/publica Difftie-Hellman
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
		kpg.initialize(ParametrosSkip.parametrosDH);
		KeyPair kp = kpg.generateKeyPair();
                    // Se recibe la clase publica
		byte[] b = new byte[dis.readInt()];
		dis.read(b);
		KeyFactory kf = KeyFactory.getInstance("DH");
		X509EncodedKeySpec x509_spec = new X509EncodedKeySpec(b);
		PublicKey pk = kf.generatePublic(x509_spec);
                    // Se envía nuestra clave publica
		b = kp.getPublic().getEncoded();
		dos.writeInt(b.length);
		dos.write(b);
                    // Termina protocolo de intercambio seguro de clave
		KeyAgreement ka = KeyAgreement.getInstance("DH");
		ka.init(kp.getPrivate());
		System.out.println(ka.doPhase(pk,true));
		b = ka.generateSecret();
		System.out.println("Clave de sesion obtenida con exito:" + new BigInteger(b));
		// Cerramos la conexi?n
		s.close();
		ss.close();
	}
}