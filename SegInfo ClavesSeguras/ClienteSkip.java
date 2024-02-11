import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import java.math.BigInteger;
public class ClienteSkip{
	public static void main(String[] args) throws Exception{
		// Abrirmos la conexi?n al servidor
		if (args.length!=1){
			System.err.println("Indique puerto como argumento");
			return;
		}
		Socket s = new Socket(InetAddress.getLocalHost(), Integer.parseInt(args[0]));
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		// Creamos el par de clave privada/publica Difftie-Hellman
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
		kpg.initialize(ParametrosSkip.parametrosDH);
		KeyPair claves = kpg.generateKeyPair();
		// Enviamos nuestra clave publica
		byte[] buffer = claves.getPublic().getEncoded();
		dos.writeInt(buffer.length);
		dos.write(buffer);
		// Recibimos la clase publica
		buffer = new byte[dis.readInt()];
		dis.read(buffer);
		KeyFactory kf = KeyFactory.getInstance("DH");
		X509EncodedKeySpec x509_spec = new X509EncodedKeySpec(buffer);
		PublicKey su_clave_publica = kf.generatePublic(x509_spec);
		// Terminamos el protocolo de intercambio de clave seguro
		KeyAgreement ka = KeyAgreement.getInstance("DH");
		ka.init(claves.getPrivate());
		ka.doPhase(su_clave_publica,true);
		buffer = ka.generateSecret();
		System.out.println("Clave de sesion obtenida con exito:" + new BigInteger(buffer));
		// Cerramos la conexion
		s.close();
	}
}