import java.net.*;
import java.io.*; // SERVIDOR
public class Servidor {
	ServerSocket ss;
	Socket s;
	OutputStream os;
	DataOutputStream dos;
	public Servidor(){
		try{
			ss = new ServerSocket(5432);
			while(true){
				s = ss.accept();
				os = s.getOutputStream();
				dos= new DataOutputStream(os);
				dos.writeUTF("Hola");
				dos.close(); s.close();
			}
		}catch(IOException ioe){}
	}
	public static void main(String [] args){
		new Servidor();
	}
}
