import java.net.*;
import java.io.*; // CLIENTE
public class Cliente{
	Socket s;
	InputStream is;
	DataInputStream dis;
	public Cliente(){
		try{
			s = new Socket("localhost", 5432);
			is = s.getInputStream();
			dis= new DataInputStream(is);
			System.out.println(dis.readUTF());
			dis.close();
			s.close();
		} catch(ConnectException ce){System.out.println("Sin conexion...");}
		catch(IOException ioe){}
	}
	public static void main(String [] args){
			new Cliente();
	}
}
