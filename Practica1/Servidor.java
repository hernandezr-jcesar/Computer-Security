/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1seguridad;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author DELL
 */
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
